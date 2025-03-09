package com.app.langking.ui.Lean

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentLessonBinding
import javax.inject.Inject

class LessonWordFragment @Inject constructor() : AppBaseFragment<FragmentLessonBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLessonBinding {
        return FragmentLessonBinding.inflate(layoutInflater, container, false)
    }

}