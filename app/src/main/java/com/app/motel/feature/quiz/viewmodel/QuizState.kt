package com.app.motel.feature.quiz.viewmodel

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
import com.history.vietnam.ultis.containsRemoveAccents
import java.util.Queue

class QuizState: AppState {
    val quizzes = MutableLiveData<List<Quiz>?>()
    val textSearch = MutableLiveData<String>(null)
    val formYear = MutableLiveData<Int>(null)
    val toYear = MutableLiveData<Int>(null)
    val typeSort = MutableLiveData<Sort>(Sort.VIEW_DESC)
    val filterListQuizzes: List<Quiz>
        get() = quizzes.value
            ?.asSequence()
            // Lọc theo từ khóa
            ?.filter { quiz ->
                val query = textSearch.value?.trim()?.lowercase()
                query.isNullOrEmpty() || quiz.title?.lowercase()?.containsRemoveAccents(query, true) == true
            }
            // Lọc theo năm từ formYear và toYear nếu đã chọn
            ?.filter { quiz ->
                val from = formYear.value
                val to = toYear.value
                val quizFrom = quiz.formPeriod
                val quizTo = quiz.toPeriod

                val isWithinFrom = from == null || (quizTo != null && quizTo >= from)
                val isWithinTo = to == null || (quizFrom != null && quizFrom <= to)

                isWithinFrom && isWithinTo
            }
            // Sắp xếp theo Sort
            ?.sortedWith(compareBy<Quiz> {
                when (typeSort.value) {
                    Sort.DATE_ASC -> it.formPeriod ?: Int.MAX_VALUE
                    Sort.DATE_DESC -> -(it.formPeriod ?: Int.MIN_VALUE)
                    Sort.VIEW_ASC -> it.playCount ?: Int.MAX_VALUE
                    Sort.VIEW_DESC -> -(it.playCount ?: Int.MIN_VALUE)
                    else -> 0
                }
            })
            ?.toList()
            ?: emptyList()


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

    enum class Sort(val value: String){
        DATE_ASC("Năm tăng"),
        DATE_DESC("Năm giảm"),
        VIEW_ASC("Lượt chơi tăng"),
        VIEW_DESC("Lượt chơi giảm");

        fun next(): Sort {
            return when (this) {
                DATE_ASC -> DATE_DESC
                DATE_DESC -> VIEW_ASC
                VIEW_ASC -> VIEW_DESC
                VIEW_DESC -> DATE_ASC
            }
        }
    }
}