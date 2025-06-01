package com.app.langking.feature.Learn.viewmodel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.langking.core.AppBaseViewModel
import com.app.langking.data.local.LocalRepository
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.TypeSpeak
import com.app.langking.data.model.UserProgress
import com.app.langking.data.model.Word
import com.app.langking.data.repository.HomeRepository
import com.app.langking.data.repository.LessonRepository
import com.app.langking.data.repository.UserRepository
import com.app.langking.ultis.DateConverter
import kotlinx.coroutines.launch
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

class LearnViewModel @Inject constructor(
    private val localRepo: LocalRepository,
    private val lessonRepository: LessonRepository,
    private val userRepository: UserRepository,
    private val homeRepository: HomeRepository,
) : AppBaseViewModel<LearnViewState, LearnViewAction, LearnViewEvent>(LearnViewState()) {

    override fun handle(action: LearnViewAction) {

    }

    init {
        getTypeSpeak()
    }

    private fun getTypeSpeak(){
        val typeSpeak = localRepo.getTypeSpeak()
        liveData.typeSpeak.postValue(typeSpeak)
    }

    fun setTypeSpeak(typeSpeak: TypeSpeak){
        liveData.typeSpeak.postValue(typeSpeak)
        localRepo.setTypeSpeak(typeSpeak)
    }

    fun initViewmodel(lesson: Lesson?){
        if(lesson?.categoryId.isNullOrEmpty() || lesson?.id.isNullOrEmpty()){
            liveData.currentLesson.postValue(lesson)
            return
        }

        viewModelScope.launch {
            val result = lessonRepository.getLessonById(lesson?.categoryId, lesson?.id, userRepository.getCurrentUserId())
            liveData.currentLesson.postValue(result.data
                ?: lesson
            )
        }
    }

    fun startTest(): Boolean{
        val words: List<Word>? = liveData.currentLesson.value?.words
        if(words.isNullOrEmpty()) return false

        val queueWord: Queue<Word> = LinkedList(words.shuffled())
        val firstWord = queueWord.poll()

        liveData.testShuffleWords.postValue(queueWord)
        liveData.testCurrentWord.postValue(firstWord)
        liveData.testRandomType = TypeTest.getRandom()

        liveData.testAllowedMistakesCount.postValue(LearnViewState.allowedMistakes)
        return true
    }

    fun submitAnswer(answer: String): Boolean{
        val currentWord = liveData.testCurrentWord.value
        val isCorrect: Boolean = if(liveData.testRandomType.isEnglish()) currentWord?.vietnamese == answer else currentWord?.english == answer

        if(!isCorrect) {
            liveData.testAllowedMistakesCount.postValue((liveData.testAllowedMistakesCount.value ?: 0) - 1)
        }

        return isCorrect
    }

    fun getAnswers(wordCorrect: Word): Queue<String> {
        val words: List<Word>? = liveData.currentLesson.value?.words

        if ((words?.size ?: 0) < 4) return LinkedList()

        val isEnglish = liveData.testRandomType.isEnglish()

        val incorrectAnswers = words!!.filter { it.id != wordCorrect.id }
            .shuffled()
            .take(3)
            .map { if (isEnglish) it.vietnamese else it.english }

        val correctAnswer = if (isEnglish) wordCorrect.vietnamese else wordCorrect.english
        val allAnswers = (incorrectAnswers + correctAnswer).shuffled()
        return LinkedList(allAnswers)
    }

    fun nextQuestion(): Boolean{
        val wordNext: Word? = liveData.testShuffleWords.value?.poll()
        if(wordNext == null) return false

        liveData.testCurrentWord.postValue(wordNext)
        liveData.testRandomType = TypeTest.getRandom()
        return true
    }

    fun submitTest() {
        val currentProgress = liveData.currentLesson.value?.userProgress

        val userProgressPush: UserProgress = liveData.currentLesson.value?.userProgress?.copy()
            ?: UserProgress(userRepository.getCurrentUserId(), liveData.currentLesson.value?.id ?: "")

        val allMistakes: Int = liveData.testAllowedMistakesCount.value ?: 0
        userProgressPush.score = if(allMistakes == 3) 100 else if(allMistakes == 2) 75 else if(allMistakes == 1) 50 else 10
        userProgressPush.dateTest = DateConverter.getCurrentDateTime()

        Log.e("submitTest", "submitTest: ${currentProgress?.score} < ${userProgressPush.score}")
        if(currentProgress == null
            || currentProgress.score < userProgressPush.score
        ){
            viewModelScope.launch {
                homeRepository.pushUserProgress(userProgressPush)
            }
        }
    }
}