package com.app.motel.feature.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.auth.AuthActivity
import com.app.motel.feature.page.viewmodel.PageViewModel
import com.app.motel.ui.adapter.SavedHorizontalAdapter
import com.app.motel.ui.adapter.SavedVerticalAdapter
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentCommentBinding
import com.history.vietnam.databinding.FragmentSavedBinding
import com.history.vietnam.feature.Home.HomeViewModel
import com.history.vietnam.ui.showDialogConfirm
import com.history.vietnam.ultis.DateConverter
import com.history.vietnam.ultis.popFragmentWithSlide
import java.util.Date
import javax.inject.Inject


class SavedFragment : AppBaseFragment<FragmentSavedBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSavedBinding {
        return FragmentSavedBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }

    private val savedAdapter = SavedVerticalAdapter(object : AppBaseAdapter.AppListener<PageInfo>(){
        override fun onClickItem(item: PageInfo, action: AppBaseAdapter.ItemAction) {
            when(action){
                AppBaseAdapter.ItemAction.CLICK -> {
                    item.apply {
                        this.action = PageInfo.Action.DETAIL
                    }.startActivity(requireActivity())
                }
                AppBaseAdapter.ItemAction.LONG_CLICK -> {
                    requireActivity().showDialogConfirm(
                        title = "Xóa",
                        content = "Bạn có chắc muốn xóa mục ${item.name} này",
                        buttonConfirm = "Xóa",
                        buttonCancel = "Hủy",
                        confirm = {
                            viewModel.userController.savePage(item, false)
                        },
                    )
                }
                else -> {}
            }
        }
    })


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    fun init(){
        views.rcv.adapter = savedAdapter

        views.btnBack.setOnClickListener {
            popFragmentWithSlide()
        }
    }

    fun listenStateViewModel(){
        viewModel.userController.state.currentUser.observe(viewLifecycleOwner){
            val saves = viewModel.userController.state.currentSavePages.values.toList().let {
                it.sortedByDescending{ page -> DateConverter.stringToDate(page.time) ?: Date(0) }
            }
            savedAdapter.updateData(saves)

            views.tvEmpty.isVisible = saves.isEmpty()
        }
    }
}