package com.app.langking.data.model

import android.content.Context
import androidx.core.content.ContextCompat
import com.app.langking.R
import com.app.langking.ultis.IDManager
import com.google.firebase.database.Exclude

data class UserProgress(
    val userId: String = Account.ACCOUNT_DEFAULT_ID,
    val lessonId: String? = null,
    var score: Int = 0,
    var dateTest: String = "",
    var dateStart: String = "",
    override val id: String = lessonId ?: "",
): RealTimeId{
    @get:Exclude
    val isStarted: Boolean
        get() = score > 0

    @get:Exclude
    val isComplete: Boolean
        get() = score >= 50

    @get:Exclude
    val progressString: String
        get() = when {
            score == 0 -> "hãy bắt đầu"
            score < 50 -> "chưa hoàn thành"
            score > 50 -> "đã hoàn thành"
            else -> "bạn hãy hoàn thành bài trước"
        }

    fun progressColor(context: Context): Int {
        return when {
            score == 0 -> ContextCompat.getColor(context, R.color.grey)
            score < 50 -> ContextCompat.getColor(context, R.color.red)
            score > 50 -> ContextCompat.getColor(context, R.color.primary)
            else -> ContextCompat.getColor(context, R.color.grey)
        }
    }
}
