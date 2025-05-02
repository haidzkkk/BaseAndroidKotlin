package com.app.motel.feature.notify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.core.AppBaseFragment
import com.app.motel.databinding.FragmentListRoomBinding
import com.app.motel.databinding.FragmentNotifyBinding
import com.app.motel.feature.notify.viewmodel.NotifyViewModel
import com.app.motel.feature.room.viewmodel.RoomViewModel
import javax.inject.Inject

class NotifyFragment @Inject constructor() : AppBaseFragment<FragmentNotifyBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentNotifyBinding {
        return FragmentNotifyBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : NotifyViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(NotifyViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

        super.onViewCreated(view, savedInstanceState)
        init()
        listenStateViewModel()
    }
    val adapter = NotificationAdapter()
    private fun init() {
        viewModel.getComplaint()

        views.rcv.adapter = adapter
    }

    private fun listenStateViewModel() {
        viewModel.liveData.complaints.observe(viewLifecycleOwner){
            val complaints = it ?: arrayListOf()
            adapter.updateData(complaints)
            views.tvEmpty.isVisible = complaints.isEmpty()
        }
    }

}