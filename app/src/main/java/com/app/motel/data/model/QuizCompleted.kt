package com.app.motel.data.model

import android.util.Log

data class QuizCompleted(
    val quiz: Quiz,
    val questions: List<Question>?,
    val answers: ArrayList<AnswerQuestion>?,
    val totalTime: Int,
) {
    var ranking: Ranking? = null

    val score: Int get() = answers?.sumOf { it.score } ?: 0
    val correctAnswers: Int get() = answers?.count { it.isCorrect } ?: 0
    val incorrectAnswers: Int get() = answers?.count { it.isIncCorrect } ?: 0
    val totalQuestions: Int get() = (answers?.size ?: 0) + (questions?.size ?: 0)

    val processCompleted get() = (((answers?.size ?: 0).toDouble() / totalQuestions.toDouble()) * 100).toInt()
    val getListQuestionAnswered get(): List<AnswerQuestion>? {
        return answers?.let {
            ArrayList(it).apply {
                addAll(
                    questions?.map { AnswerQuestion(
                        question = it,
                        answerQuestionId = null,
                        secondsToAnswer = null,
                        maxSecondsToAnswer = null,
                    ) } ?: arrayListOf()
                )
            }
        }
    }
}