package com.app.motel.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.motel.AppApplication
import com.app.motel.core.AppBaseFragment
import com.app.motel.databinding.FragmentGeneralBoardingHouseBinding
import javax.inject.Inject

class GeneralBoardingHouseFragment @Inject constructor() : AppBaseFragment<FragmentGeneralBoardingHouseBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGeneralBoardingHouseBinding {
        return FragmentGeneralBoardingHouseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
    }
}