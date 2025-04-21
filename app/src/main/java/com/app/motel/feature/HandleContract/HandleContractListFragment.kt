package com.app.motel.feature.HandleContract

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.ultis.navigateFragmentWithSlide
import com.app.motel.core.AppBaseAdapter
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Room
import com.app.motel.databinding.FragmentCreateContractListBinding
import com.app.motel.databinding.FragmentHandleContractListBinding
import com.app.motel.feature.CreateContract.CreateContractFormFragment
import com.app.motel.feature.CreateContract.RoomContractAdapter
import com.app.motel.feature.CreateContract.viewmodel.CreateContractViewModel
import com.app.motel.feature.HandleContract.viewmodel.HandleContractViewModel
import com.app.motel.ui.custom.CustomTabBar
import com.google.gson.Gson
import javax.inject.Inject

class HandleContractListFragment @Inject constructor() : AppBaseFragment<FragmentHandleContractListBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHandleContractListBinding {
        return FragmentHandleContractListBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HandleContractViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HandleContractViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

        super.onViewCreated(view, savedInstanceState)
        init()
        listenStateViewModel()
    }
    lateinit var adapter: HandleContractAdapter

    private fun init() {
        viewModel.getContracts()
        viewModel.setCurrentStateContract(views.tabBar.currentPosition)
        views.tabBar.setOnTabSelectedListener(object: CustomTabBar.OnTabSelectedListener{
            override fun onTabSelected(position: Int) {
                viewModel.setCurrentStateContract(position)
            }
        })
        adapter = HandleContractAdapter(object: AppBaseAdapter.AppListener<Contract>(){
            override fun onClickItem(item: Contract, action: AppBaseAdapter.ItemAction) {
                when(action){
                    AppBaseAdapter.ItemAction.CLICK -> {
                        DetailContractBottomSheet(item).show(
                            parentFragmentManager, DetailContractBottomSheet::class.java.simpleName
                        )
                    }
                    AppBaseAdapter.ItemAction.EDIT -> {
                        RefreshContractBottomSheet(item).show(
                            parentFragmentManager, RefreshContractBottomSheet::class.java.simpleName
                        )
                    }
                    AppBaseAdapter.ItemAction.DELETE -> {
                        navigateFragmentWithSlide(R.id.handleContractTerminalFragment, Bundle().apply {
                            putString(HandleContractTerminalFragment.CONTRACT_KEY, Gson().toJson(item))
                        })
                    }
                    else -> {}
                }
            }
        })
        views.rcv.adapter = adapter
    }

    private fun listenStateViewModel() {
        viewModel.liveData.contracts.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                adapter.updateData(viewModel.liveData.getContractToState)
            }
        }
        viewModel.liveData.currentStateContract.observe(viewLifecycleOwner){
            adapter.updateData(viewModel.liveData.getContractToState)
        }
    }
}