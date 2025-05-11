package com.app.motel.feature.historicalFigure.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.app.motel.feature.setting.SettingController
import com.app.motel.feature.setting.TextFont
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseBottomSheet
import com.history.vietnam.databinding.DialogCustomTextBinding
import javax.inject.Inject

class HistoricalFigureCustomBottomFragment: AppBaseBottomSheet<DialogCustomTextBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogCustomTextBinding {
         return DialogCustomTextBinding.inflate(inflater, container, false)
    }

    override val isBorderRadiusTop: Boolean
        get() = false

    @Inject
    lateinit var settingController: SettingController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        init()
        listenStateViewModel()
    }

    private fun init() {
        views.tvTextSize10.setOnClickListener {
            settingController.setTextSize(10)
        }
        views.tvTextSize11.setOnClickListener {
            settingController.setTextSize(11)
        }
        views.tvTextSize12.setOnClickListener {
            settingController.setTextSize(12)
        }
        views.tvTextSize13.setOnClickListener {
            settingController.setTextSize(13)
        }
        views.tvTextSize14.setOnClickListener {
            settingController.setTextSize(14)
        }
        views.tvTextSize15.setOnClickListener {
            settingController.setTextSize(15)
        }
        views.tvFontPoppin.setOnClickListener {
            settingController.setTextFont(TextFont.POPPINS)
        }
        views.tvFontTimesNewRomen.setOnClickListener {
            settingController.setTextFont(TextFont.TIME_NEW_ROMAN)
        }
        views.tvFontArimo.setOnClickListener {
            settingController.setTextFont(TextFont.ARIMO)
        }
        views.tvWhiteBackground.setOnClickListener {
            settingController.setBackground(R.color.background1)
        }
        views.tvGreyBackground.setOnClickListener {
            settingController.setBackground(R.color.background2)
        }
        views.tvBlackBackground.setOnClickListener {
            settingController.setBackground(R.color.background_permanent_1)
        }
    }

    private fun listenStateViewModel() {
        settingController.state.textSize.observe(viewLifecycleOwner){
            handleSelectTextSize(it)
        }
        settingController.state.textFont.observe(viewLifecycleOwner){
            handleSelectTextFont(it)
        }
        settingController.state.backgroundColor.observe(viewLifecycleOwner){
            handleSelectBackgroundColor(it)
        }
    }

    private fun handleSelectTextSize(value: Int){
        views.tvTextSize10.setBackgroundResource(R.drawable.background_border_default)
        views.tvTextSize11.setBackgroundResource(R.drawable.background_border_default)
        views.tvTextSize12.setBackgroundResource(R.drawable.background_border_default)
        views.tvTextSize13.setBackgroundResource(R.drawable.background_border_default)
        views.tvTextSize14.setBackgroundResource(R.drawable.background_border_default)
        views.tvTextSize15.setBackgroundResource(R.drawable.background_border_default)

        views.tvTextSize10.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)
        views.tvTextSize11.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)
        views.tvTextSize12.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)
        views.tvTextSize13.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)
        views.tvTextSize14.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)
        views.tvTextSize15.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)

        when(value){
            10 -> {
                views.tvTextSize10.setBackgroundResource(R.drawable.background_border_1)
                views.tvTextSize10.backgroundTintList = null
            }
            11 -> {
                views.tvTextSize11.setBackgroundResource(R.drawable.background_border_1)
                views.tvTextSize11.backgroundTintList = null
            }
            12 -> {
                views.tvTextSize12.setBackgroundResource(R.drawable.background_border_1)
                views.tvTextSize12.backgroundTintList = null
            }
            13 -> {
                views.tvTextSize13.setBackgroundResource(R.drawable.background_border_1)
                views.tvTextSize13.backgroundTintList = null
            }
            14 -> {
                views.tvTextSize14.setBackgroundResource(R.drawable.background_border_1)
                views.tvTextSize14.backgroundTintList = null
            }
            15 -> {
                views.tvTextSize15.setBackgroundResource(R.drawable.background_border_1)
                views.tvTextSize15.backgroundTintList = null
            }
        }
    }

    private fun handleSelectTextFont(font: TextFont){
        views.tvFontPoppin.setBackgroundResource(R.drawable.background_border_default)
        views.tvFontTimesNewRomen.setBackgroundResource(R.drawable.background_border_default)
        views.tvFontArimo.setBackgroundResource(R.drawable.background_border_default)

        views.tvFontPoppin.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)
        views.tvFontTimesNewRomen.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)
        views.tvFontArimo.backgroundTintList = ContextCompat.getColorStateList(requireContext(), android.R.color.transparent)

        when(font){
            TextFont.POPPINS -> {
                views.tvFontPoppin.setBackgroundResource(R.drawable.background_border_1)
                views.tvFontPoppin.backgroundTintList = null
            }
            TextFont.TIME_NEW_ROMAN -> {
                views.tvFontTimesNewRomen.setBackgroundResource(R.drawable.background_border_1)
                views.tvFontTimesNewRomen.backgroundTintList = null
            }
            TextFont.ARIMO -> {
                views.tvFontArimo.setBackgroundResource(R.drawable.background_border_1)
                views.tvFontArimo.backgroundTintList = null
            }
        }
    }

    private fun handleSelectBackgroundColor(color: Int){
        views.tvWhiteBackground.setBackgroundResource(R.drawable.background_border_default)
        views.tvGreyBackground.setBackgroundResource(R.drawable.background_border_default)
        views.tvBlackBackground.setBackgroundResource(R.drawable.background_border_default)

        when(color){
            R.color.background1 -> {
                views.tvWhiteBackground.setBackgroundResource(R.drawable.background_border_1)
            }
            R.color.background2 -> {
                views.tvGreyBackground.setBackgroundResource(R.drawable.background_border_1)
            }
            R.color.background_permanent_1 -> {
                views.tvBlackBackground.setBackgroundResource(R.drawable.background_border_1)
            }
        }
    }
}