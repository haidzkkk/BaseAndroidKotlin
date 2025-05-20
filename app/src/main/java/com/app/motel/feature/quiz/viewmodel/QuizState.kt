package com.app.motel.feature.quiz.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.AnswerQuestion
import com.app.motel.data.model.Question
import com.app.motel.data.model.Quiz
import com.app.motel.data.model.QuizCompleted
import com.app.motel.data.model.Ranking
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Resource
import com.history.vietnam.ultis.DateConverter
import com.history.vietnam.ultis.DateConverter.isToday
import java.util.Queue

class QuizState: AppState {
    val quizzes = MutableLiveData<List<Quiz>?>()

    val currentQuiz = MutableLiveData<Resource<Quiz>>()

    val questions = MutableLiveData<Queue<Question>>()
    val answers = MutableLiveData<ArrayList<AnswerQuestion>>()
    val currentQuestion = MutableLiveData<Question>()
    val currentAnswerId = MutableLiveData<AnswerQuestion>()
    var secondsAnswerPerQuestion: Int? = null
    val timer = MutableLiveData<Int>()
    val timeToAnswerQuestion = MutableLiveData<Int>()

    val quizCompleted = MutableLiveData<Resource<QuizCompleted>>(Resource.Initialize())

    val isTodayFilterRanking = MutableLiveData<Boolean>(true)
    val filterListRankings: List<Ranking> get() = currentQuiz.value?.data?.rankings?.filter {
        if(isTodayFilterRanking.value == true){
            return@filter DateConverter.stringToDate(it.time)?.isToday() == true
        }
        true
    } ?: arrayListOf()
}