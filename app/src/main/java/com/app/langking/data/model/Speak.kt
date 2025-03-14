package com.app.langking.data.model

import android.speech.tts.TextToSpeech
import java.util.Locale

data class Speak(
    val word: Word,
    var language: Locale = Locale.US,
    var speed: Float = 1f,
    val queueMode: Int = TextToSpeech.QUEUE_FLUSH,
    var durationMillisecond: Int = 0,
    val typeSpeak: TypeSpeak = TypeSpeak()
) {

    private var speedCore: Float = speed
    private var languageCore: Locale = language

    private fun handleGetContent(): String?{
        speed = speedCore
        language = languageCore

        return (if(typeSpeak.isEnglish) {
            typeSpeak.isEnglish = false
            word.english
        }else if(typeSpeak.isEnglishSlow) {
            typeSpeak.isEnglishSlow = false
            speed = 0.3f
            word.english
        }else if(typeSpeak.isVietnamese) {
            language = Locale.forLanguageTag("vi")
            typeSpeak.isVietnamese = false
            word.vietnamese
        }else if(typeSpeak.isEnglishDesc) {
            typeSpeak.isEnglishDesc = false
            word.description ?: ""
        }else if(typeSpeak.isVietnameseDesc) {
            language = Locale.forLanguageTag("vi")
            typeSpeak.isVietnameseDesc = false
            word.descriptionVietnamese ?: ""
        }else{
            null;
        }).apply {
            if(!typeSpeak.isContinue) durationMillisecond = 2000
        }
    }

    val content: String?
        get() = handleGetContent()?.let {
            if (durationMillisecond > 0) {
                """
            <speak>
                $it <break time="${durationMillisecond}ms"/>
            </speak>
            """.trimIndent()
            } else {
                it
            }
        }



}
