package com.app.langking.ui.trips

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentTripBinding

class TripFragment : AppBaseFragment<FragmentTripBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTripBinding {
        return FragmentTripBinding.inflate(inflater, container, false)
    }
}