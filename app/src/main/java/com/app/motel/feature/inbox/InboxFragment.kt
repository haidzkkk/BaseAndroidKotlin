package com.app.motel.feature.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.motel.core.AppBaseFragment
import com.app.motel.databinding.FragmentInboxBinding


class InboxFragment : AppBaseFragment<FragmentInboxBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInboxBinding {
        return FragmentInboxBinding.inflate(inflater, container, false)
    }
}