package com.app.langking.ui.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentInboxBinding


class InboxFragment : AppBaseFragment<FragmentInboxBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInboxBinding {
        return FragmentInboxBinding.inflate(inflater, container, false)
    }
}