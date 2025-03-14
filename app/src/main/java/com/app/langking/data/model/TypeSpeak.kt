package com.app.langking.data.model

data class TypeSpeak(
    var isEnglish: Boolean = false,
    var isEnglishSlow: Boolean = false,
    var isVietnamese: Boolean = false,
    var isEnglishDesc: Boolean = false,
    var isVietnameseDesc: Boolean = false,
) {
    val isContinue: Boolean
        get() = isEnglish
                || isEnglishSlow
                || isVietnamese
                || isEnglishDesc
                || isVietnameseDesc
}