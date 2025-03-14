package com.app.langking.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.app.langking.data.model.TypeSpeak
import com.app.langking.ultis.AppConstants
import javax.inject.Inject

class LocalRepository @Inject constructor(private val prefs: SharedPreferences) {

    fun getTypeSpeak(): TypeSpeak {
        val typeSpeak = TypeSpeak(
            isEnglish = false,
            isEnglishSlow = false,
            isVietnamese = false,
            isEnglishDesc = false,
            isVietnameseDesc = false
        )
        typeSpeak.isEnglish = prefs.getBoolean(AppConstants.isEnglish, false)
        typeSpeak.isEnglishSlow = prefs.getBoolean(AppConstants.isEnglishSlow, false)
        typeSpeak.isVietnamese = prefs.getBoolean(AppConstants.isVietnamese, false)
        typeSpeak.isEnglishDesc = prefs.getBoolean(AppConstants.isEnglishDesc, false)
        typeSpeak.isVietnameseDesc = prefs.getBoolean(AppConstants.isVietnameseDesc, false)
        return typeSpeak;
    }

    fun setTypeSpeak(typeSpeak: TypeSpeak) {
        prefs.edit().apply {
            putBoolean(AppConstants.isEnglish, typeSpeak.isEnglish)
            putBoolean(AppConstants.isEnglishSlow, typeSpeak.isEnglishSlow)
            putBoolean(AppConstants.isVietnamese, typeSpeak.isVietnamese)
            putBoolean(AppConstants.isEnglishDesc, typeSpeak.isEnglishDesc)
            putBoolean(AppConstants.isVietnameseDesc, typeSpeak.isVietnameseDesc)
            apply()
        }
    }

}