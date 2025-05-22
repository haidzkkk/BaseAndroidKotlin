package com.app.motel.feature.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.motel.data.model.AppNotification
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.notification.viewmodel.NotificationViewModel
import com.app.motel.feature.territory.viewmodel.TerritoryViewModel
import com.app.motel.ui.adapter.SavedVerticalAdapter
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentNotificationBinding
import com.history.vietnam.databinding.FragmentTerritoryCommentBinding
import com.history.vietnam.feature.Home.HomeViewModel
import com.history.vietnam.ui.showDialogConfirm
import com.history.vietnam.ultis.DateConverter
import com.history.vietnam.ultis.popFragmentWithSlide
import java.util.Date
import javax.inject.Inject

class NotificationFragment : AppBaseFragment<FragmentNotificationBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : NotificationViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(NotificationViewModel::class.java)
    }

    private val adapter = NotificationAdapter(object : AppBaseAdapter.AppListener<AppNotification>(){
        override fun onClickItem(item: AppNotification, action: AppBaseAdapter.ItemAction) {

            viewModel.handleDataRedirect(item)
            viewModel.updateNotificationRead(item)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        init()
        listenStateViewModel()
    }

    fun init() {
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        views.rcv.addItemDecoration(dividerItemDecoration)
        views.rcv.adapter = adapter

        views.btnBack.setOnClickListener {
            popFragmentWithSlide()
        }
    }

    fun listenStateViewModel() {
        viewModel.liveData.notifications.observe(viewLifecycleOwner){
            val notify = it?.let {
                it.sortedByDescending{ page -> DateConverter.stringToDate(page.time) ?: Date(0) }
            }
            adapter.updateData(notify)
            views.tvEmpty.isVisible = notify.isNullOrEmpty()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setAllNotificationRead()
    }
}