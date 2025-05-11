package com.app.motel.feature.setting

import com.app.motel.data.repository.SettingRepository
import javax.inject.Inject

class SettingController @Inject constructor(
    private val settingRepository: SettingRepository
) {
    val state: SettingState = SettingState()

   init {
       getSetting()
   }

    private fun getSetting(){
        state.textSize.postValue(settingRepository.getTextSize())
        state.textFont.postValue(TextFont.fromValue(settingRepository.getTextFont()))
        state.backgroundColor.postValue(settingRepository.getBackgroundColor())
    }

    fun setTextSize(textSize: Int) {
        settingRepository.setTextSize(textSize)
        state.textSize.postValue(textSize)
    }

    fun setTextFont(font: TextFont){
        settingRepository.setTextFont(font.value)
        state.textFont.postValue(font)
    }

    fun setBackground(color: Int){
        settingRepository.setBackgroundColor(color)
        state.backgroundColor.postValue(color)
    }
}