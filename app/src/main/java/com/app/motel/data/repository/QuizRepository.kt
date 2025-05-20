package com.app.motel.data.repository

import com.app.motel.data.model.Quiz
import com.app.motel.data.model.QuizFullValue
import com.app.motel.data.model.Ranking
import com.app.motel.data.model.Territory
import com.app.motel.data.network.FirebaseManager
import com.app.motel.ultis.IDManager
import com.history.vietnam.data.model.Resource
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val firebaseManager: FirebaseManager
) {
    suspend fun getQuizzes() = firebaseManager.getList(AppConstants.FIREBASE_QUIZ_PATH, Quiz::class.java)

    suspend fun getRankQuizzes(quizId: String) = firebaseManager.getList("${AppConstants.FIREBASE_QUIZ_PATH}/$quizId/${AppConstants.FIREBASE_RANKING_NODE}", Ranking::class.java)

    suspend fun submitQuizTest(quizId: String, ranking: Ranking): Resource<Ranking>{
        val path = "${AppConstants.FIREBASE_QUIZ_PATH}/$quizId/${AppConstants.FIREBASE_RANKING_NODE}/${ranking.id}"

        if(ranking.userId != null){
            val topUserRanking = firebaseManager.getObject(path, Ranking::class.java).data
            if(topUserRanking?.score != null && ranking.score != null &&
                topUserRanking.score >= ranking.score){
                return Resource.Success(ranking.copy(id = IDManager.createID()))
            }
        }

        return firebaseManager.overwrite(path, ranking)
    }

    suspend fun incrementPlayedCount(quizId: String?): Resource<QuizFullValue>{
        if(quizId.isNullOrEmpty()) return Resource.Error("Quiz not found")

        val path = "${AppConstants.FIREBASE_QUIZ_PATH}/$quizId"
        val quiz = firebaseManager.getObject(path, QuizFullValue::class.java).data ?: return Resource.Error("Quiz not found")

        val updatedQuiz = quiz.copy(playCount = (quiz.playCount ?: 0) + 1)
        return firebaseManager.overwrite(path, updatedQuiz)
    }

}