package com.app.motel.feature.quiz.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.AnswerQuestion
import com.app.motel.data.model.Question
import com.app.motel.data.model.Quiz
import com.app.motel.data.model.QuizCompleted
import com.app.motel.data.model.Ranking
import com.app.motel.data.repository.QuizRepository
import com.app.motel.data.repository.UserRepository
import com.app.motel.feature.profile.UserController
import com.app.motel.ultis.IDManager
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import com.history.vietnam.ultis.DateConverter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import java.util.LinkedList
import javax.inject.Inject

class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val userRepository: UserRepository,
    val userController: UserController,
): AppBaseViewModel<QuizState, QuizViewAction, QuizViewEvent>(QuizState()) {
    override fun handle(action: QuizViewAction) {

    }

    fun getQuizzes() {
        viewModelScope.launch {
            val quizzes = quizRepository.getQuizzes()
            liveData.quizzes.postValue(quizzes.data)
        }
    }

    fun initQuiz(quizId: String?){
        viewModelScope.launch {
            if(quizId.isNullOrEmpty()){
                liveData.currentQuiz.postValue(Resource.Error("Quiz không tồn tại"))
                return@launch
            }
            val quiz = quizRepository.getQuiz(quizId)
            liveData.currentQuiz.postValue(quiz)
        }
    }

    fun initQuizTest(){
        val currentQuiz = liveData.currentQuiz.value?.data
        val listQuestion = currentQuiz?.questions?.map {
            it.copy(image = it.image ?: currentQuiz.image)
        }?.shuffled() ?: arrayListOf()

        liveData.questions.postValue(LinkedList(listQuestion))
        liveData.answers.postValue(arrayListOf())
        liveData.secondsAnswerPerQuestion = (currentQuiz?.durationSeconds ?: 0) / listQuestion.size

        startCountDownTimer(currentQuiz?.durationSeconds)

        viewModelScope.launch {
            quizRepository.incrementPlayedCount(currentQuiz?.id)
        }
    }

    fun clearQuizTest(){
        liveData.questions.postValue(null)
        liveData.answers.postValue(null)
        liveData.currentQuestion.postValue(null)
        liveData.currentAnswerId.postValue(null)
        liveData.timer.postValue(null)
        liveData.timeToAnswerQuestion.postValue(null)
        liveData.secondsAnswerPerQuestion = null

        stopCountDownTimer()
        stopTimeToAnswerQuestion()
    }

    fun nextQuestion(){
        if(liveData.quizCompleted.value?.isInitialize() != true) return

        val question: Question? = liveData.questions.value?.poll()

        val isCompleted = question == null
        if(isCompleted){
            submitQuiz()
            return
        }

        liveData.currentQuestion.postValue(question)
        startTimeToAnswerQuestion()
    }

    fun submitAnswer(question: Question, answerId: String?){
        stopTimeToAnswerQuestion()
        liveData.currentQuestion.postValue(null)

        val secondsToAnswer = liveData.timeToAnswerQuestion.value ?: 0

        val answer = AnswerQuestion(
            question = question,
            answerQuestionId = answerId ?: "",
            secondsToAnswer = secondsToAnswer,
            maxSecondsToAnswer = liveData.secondsAnswerPerQuestion ?: 0
        )
        liveData.answers.postValue(liveData.answers.value.apply {
            this?.add(answer)
        })
        liveData.currentAnswerId.postValue(answer)

        viewModelScope.launch {
            delay(2000)
            nextQuestion()
        }
    }

    private var countDownTimer: CountDownTimer? = null
    private fun startCountDownTimer(totalSecond: Int?){
        stopCountDownTimer()
        if(totalSecond == null) {
            return
        }

        val totalMillisecond = totalSecond * 1000L

        countDownTimer = object : CountDownTimer(totalMillisecond, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                liveData.timer.postValue(secondsLeft.toInt())
            }
            override fun onFinish() {
                liveData.timer.postValue(0)
                submitQuiz()
            }
        }
        countDownTimer?.start()
    }

    private fun stopCountDownTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private var timerJob: Job? = null
    private fun startTimeToAnswerQuestion() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            var elapsed = 0
            while (isActive) {
                liveData.timeToAnswerQuestion.postValue(elapsed)
                delay(1000)
                elapsed++
            }
        }
    }

    private fun stopTimeToAnswerQuestion() {
        timerJob?.cancel()
    }

    fun submitQuiz() {
        stopCountDownTimer()
        val totalTime = (liveData.currentQuiz.value?.data?.durationSeconds ?: 0) - (liveData.timer.value ?: 0)
        val answers = liveData.answers.value
        val questions = ArrayList(liveData.questions.value ?: arrayListOf()).apply { liveData.currentQuestion.value?.let { add(0,  it) } } // current Queue question and current question has poll
        val quiz = liveData.currentQuiz.value?.data

        if(totalTime < 0
            || quiz?.id == null
            || (answers.isNullOrEmpty() && questions.isEmpty())
            ){
            liveData.quizCompleted.postValue(Resource.Error(
                message = "Không thể hoàn thành quiz"
            ))
            return
        }

        val quizCompleted = QuizCompleted(
            quiz = quiz,
            questions = questions,
            answers = answers,
            totalTime = totalTime,
        )

        viewModelScope.launch {
            val ranking = quizRepository.submitQuizTest(
                quiz.id,
                Ranking(
                    id = userController.state.currentUser.value?.data?.id ?: IDManager.createID(),
                    userId = userController.state.currentUser.value?.data?.id,
                    score = quizCompleted.score,
                    time = DateConverter.getCurrentStringDateTime(),
                )
            ).apply {
                this.data?.user = userController.state.currentUser.value?.data
            }
            quizCompleted.ranking = ranking.data
            liveData.quizCompleted.postValue(Resource.Success(quizCompleted))
        }
    }

    private var soundPlayer: MediaPlayer? = null
    fun playCorrectSound(context: Context, sound: Int) {
        soundPlayer = MediaPlayer.create(context, sound)
        soundPlayer?.apply {
            start()
            setOnCompletionListener {
                it.release()
                soundPlayer = null
            }
        }
    }

    fun getRankQuiz(){
        viewModelScope.launch {
            val quiz = liveData.currentQuiz.value?.data?.copy() ?: return@launch

            val rankings = quizRepository.getRankQuizzes(quiz.id ?: "").let {
                it.data?.forEach { ranking ->
                    ranking.user = ranking.userId?.let { userRepository.getUserById(it) }
                }
                it.data?.sortedWith(compareByDescending<Ranking> { it.score }
                    .thenBy { ranking ->
                        ranking.time?.let { timeStr -> DateConverter.stringToDate(timeStr) } ?: Date(0)
                    })
            }
            quiz.rankings = rankings?.let { ArrayList(it) }

            liveData.currentQuiz.postValue(Resource.Success(quiz))
        }
    }
}