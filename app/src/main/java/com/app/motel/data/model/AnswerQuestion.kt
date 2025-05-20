package com.app.motel.data.model

data class AnswerQuestion(
    val question: Question,
    val answerQuestionId: String?,
    val secondsToAnswer: Int?,
    val maxSecondsToAnswer: Int?,
) {
    val isAnswer get() = answerQuestionId != null
    val isNotAnswer get() = answerQuestionId == null
    val isCorrect get() = answerQuestionId != null &&  answerQuestionId == question.correctAnswerId
    val isIncCorrect get() = answerQuestionId != null && answerQuestionId != question.correctAnswerId

    val score: Int get() = if (isIncCorrect) 0 else {
        val maxScore = 100
        val score = (((maxSecondsToAnswer ?: 0) - (secondsToAnswer ?: 0)).toFloat() / (maxSecondsToAnswer ?: 0) * maxScore).toInt()
        Math.max(0, score)
    }
}