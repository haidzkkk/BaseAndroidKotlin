package com.app.motel.data.repository

import android.content.SharedPreferences
import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.R
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    fun setTextSize(textSize: Int) {
        sharedPreferences.edit().putInt(AppConstants.SHARED_PREFERENCES_TEXT_SIZE_KEY, textSize).apply()
    }

    fun getTextSize(): Int {
        return sharedPreferences.getInt(AppConstants.SHARED_PREFERENCES_TEXT_SIZE_KEY, 14)
    }

    fun setTextFont(font: Int){
        sharedPreferences.edit().putInt(AppConstants.SHARED_PREFERENCES_TEXT_FONT_KEY, font).apply()
    }

    fun getTextFont(): Int {
        return sharedPreferences.getInt(AppConstants.SHARED_PREFERENCES_TEXT_FONT_KEY, R.font.poppins)
    }

    fun setBackgroundColor(color: Int){
        sharedPreferences.edit().putInt(AppConstants.SHARED_PREFERENCES_BACKGROUND_COLOR_KEY, color).apply()
    }

    fun getBackgroundColor(): Int {
        return sharedPreferences.getInt(AppConstants.SHARED_PREFERENCES_BACKGROUND_COLOR_KEY, R.color.background1)
    }
}