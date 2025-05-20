package com.app.motel.data.model

import com.google.firebase.database.Exclude
import com.history.vietnam.data.model.User

data class Quiz(
    override val id: String? = null,
    val title: String? = null,
    val formPeriod: Int? = null,
    val toPeriod: Int? = null,
    val period: String? = null,
    val questionCount: Int? = null,
    val durationMinutes: Int? = null,
    val playCount: Int? = null,
    val image: String? = null,
    val questions: List<Question>? = null,
): RealTimeId{

    @get:Exclude
    var rankings: ArrayList<Ranking>? = null

    val durationSeconds get() = (durationMinutes ?: 0) * 60
}

data class Question(
    override val id: String? = null,
    val text: String? = null,
    val image: String? = null,
    val answers: List<Answer>? = null,
    val correctAnswerId: String? = null,
    val userAnswerId: String? = null
): RealTimeId

data class Answer(
    override val id: String? = null,
    val text: String? = null,
): RealTimeId

data class Ranking(
    override val id: String? = null,
    val userId: String? = null,
    val score: Int? = null,
    val time: String? = null,
): RealTimeId {

    @get:Exclude
    var user: User? = null
}

data class QuizFullValue(
    override val id: String? = null,
    val title: String? = null,
    val formPeriod: Int? = null,
    val toPeriod: Int? = null,
    val period: String? = null,
    val questionCount: Int? = null,
    val durationMinutes: Int? = null,
    val playCount: Int? = null,
    val image: String? = null,
    val questions: List<Question>? = null,
    var rankings: HashMap<String, Ranking>? = null
): RealTimeId{
}