package com.example.travle.ui.trips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travle.R
import com.example.travle.core.TravleBaseFragment
import com.example.travle.databinding.FragmentTripBinding
import com.example.travle.databinding.FragmentWishListBinding

class TripFragment : TravleBaseFragment<FragmentTripBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTripBinding {
        return FragmentTripBinding.inflate(inflater, container, false)
    }
}