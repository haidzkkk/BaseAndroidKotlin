package com.app.langking.feature.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentCongurrationsBinding
import com.app.langking.feature.MainActivity

class CongurrationsFragment : AppBaseFragment<FragmentCongurrationsBinding>() {

    val handler = Handler(Looper.getMainLooper())
    var start = 0
    var end = 3
    private val runnable = object : Runnable {
        var start = 1
        override fun run() {
            handler.postDelayed(this, 1000)
            start++
            if (start == end) {
                startToHomeActivity()
            }
        }
    }
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCongurrationsBinding {
        return FragmentCongurrationsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler.post(runnable)
        views.btnContinue.setOnClickListener{
            startToHomeActivity()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    private fun startToHomeActivity() {
        requireActivity().finishAffinity()
        requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
    }

}