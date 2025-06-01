package com.app.langking.data.model

import com.app.langking.ultis.IDManager
import com.google.firebase.database.Exclude

data class Lesson(
    override val id: String = IDManager.createID(),
    val categoryId: String? = null,
    val name: String? = null,
    val content: String? = "",
    val position: Int? = null,

    @get:Exclude
    var words: List<Word>? = null,

    @get:Exclude
    var userProgress: UserProgress? = null,
): RealTimeId
