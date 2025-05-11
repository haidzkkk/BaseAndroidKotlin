package com.app.motel.feature.setting

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.history.vietnam.R
import com.history.vietnam.core.AppState

class SettingState: AppState {
     val textSize = MutableLiveData<Int>(13)
    val textFont = MutableLiveData<TextFont>(TextFont.POPPINS)
    val backgroundColor = MutableLiveData<Int>(R.color.background1)


    fun getTextSize(context: Context) = textSize.value?.toFloat()  ?: context.resources.getDimension(R.dimen.TEXT_SIZE_DEFAULT)
    fun getTextFont(context: Context) = ResourcesCompat.getFont(context, textFont.value?.value ?: R.font.poppins)
    fun getBackgroundColor(context: Context) = ContextCompat.getColor(context, backgroundColor.value ?: R.color.background1)
    fun getTextColor(context: Context) = ContextCompat.getColor(context, if(isDarkMode) R.color.textColor_permanent_1 else R.color.textColor2)
    fun getHintTextColor(context: Context) = ContextCompat.getColor(context, if(isDarkMode) R.color.textColor_permanent_2 else R.color.textColor3)
    val isDarkMode get() = backgroundColor.value == R.color.background_permanent_1
}

enum class TextFont(val value: Int){
    POPPINS(R.font.poppins),
    TIME_NEW_ROMAN(R.font.times_new_romans),
    ARIMO(R.font.arimos);

    companion object{
        fun fromValue(value: Int): TextFont {
            return when (value) {
                R.style.FontPoppins -> POPPINS
                R.style.FontTimesNewRoman -> TIME_NEW_ROMAN
                R.style.FontArimo -> ARIMO
                else -> POPPINS
            }
        }
    }
}