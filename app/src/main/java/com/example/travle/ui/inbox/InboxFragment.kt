package com.example.travle.ui.inbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travle.R
import com.example.travle.core.TravleBaseFragment
import com.example.travle.databinding.FragmentInboxBinding
import com.example.travle.databinding.FragmentTripBinding


class InboxFragment : TravleBaseFragment<FragmentInboxBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInboxBinding {
        return FragmentInboxBinding.inflate(inflater, container, false)
    }
}