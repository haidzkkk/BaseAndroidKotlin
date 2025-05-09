package com.app.langking.data.model

import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import java.util.Locale
import java.util.Random

data class Speak(
    val word: Word,
    var voice: Voice = if (Random().nextBoolean()) voiceMale else voiceFeMale,
    var speed: Float = 1f,
    val queueMode: Int = TextToSpeech.QUEUE_FLUSH,
    var durationMillisecond: Int = 0,
    val typeSpeak: TypeSpeak = TypeSpeak()
) {

    private var speedCore: Float = speed
    private var voiceCore: Voice = voice

    private fun handleGetContent(): String?{
        speed = speedCore
        voice = voiceCore

        return (if(typeSpeak.isEnglish) {
            typeSpeak.isEnglish = false
            word.english
        }else if(typeSpeak.isEnglishSlow) {
            typeSpeak.isEnglishSlow = false
            speed = 0.3f
            word.english
        }else if(typeSpeak.isVietnamese) {
            voice = voiceVietnamese
            typeSpeak.isVietnamese = false
            word.vietnamese
        }else if(typeSpeak.isEnglishDesc) {
            typeSpeak.isEnglishDesc = false
            word.description ?: ""
        }else if(typeSpeak.isVietnameseDesc) {
            voice = voiceVietnamese
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


    companion object{
        val voiceMale = Voice("hi-in-x-hie-local", Locale("hi_IN"), 400, 200, false, setOf())
        val voiceFeMale = Voice( "en-us-x-sfg#female_1-local", Locale.US,400,200,false, setOf())
        val voiceVietnamese = Voice("en-us-x-sfg#female_1-local", Locale.forLanguageTag("vi"),400,200,false, setOf())
    }
}
