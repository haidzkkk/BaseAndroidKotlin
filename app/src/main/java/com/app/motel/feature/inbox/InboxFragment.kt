package com.history.vietnam.feature.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentInboxBinding


class InboxFragment : AppBaseFragment<FragmentInboxBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInboxBinding {
        return FragmentInboxBinding.inflate(inflater, container, false)
    }
}