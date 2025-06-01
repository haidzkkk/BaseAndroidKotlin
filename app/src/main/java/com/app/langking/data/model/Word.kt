package com.app.langking.data.model

import com.app.langking.ultis.IDManager
import kotlin.random.Random

data class Word(
    override val id: String = IDManager.createID(),
    val lessonId: String? = null,
    val english: String? = null,
    val vietnamese: String? = null,
    val pronunciation: String? = null,
    val audioUrl: String? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val descriptionVietnamese: String? = null,
    val position: Int? = null,
): RealTimeId
