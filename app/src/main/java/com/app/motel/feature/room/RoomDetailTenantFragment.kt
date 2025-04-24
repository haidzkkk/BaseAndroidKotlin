package com.app.motel.feature.room

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.ultis.navigateFragmentWithSlide
import com.app.motel.core.AppBaseAdapter
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.model.Room
import com.app.motel.data.model.Service
import com.app.motel.data.model.Tenant
import com.app.motel.databinding.FragmentRoomDetailTenantBinding
import com.app.motel.feature.createContract.CreateContractFormFragment
import com.app.motel.feature.room.viewmodel.RoomViewModel
import com.app.motel.feature.service.ServiceFormFragment
import com.google.gson.Gson
import javax.inject.Inject

class RoomDetailTenantFragment @Inject constructor() : AppBaseFragment<FragmentRoomDetailTenantBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRoomDetailTenantBinding {
        return FragmentRoomDetailTenantBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel : RoomViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(RoomViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

        super.onViewCreated(view, savedInstanceState)
        listenStateViewModel()
        adapter = RoomTenantAdapter(
            object : AppBaseAdapter.AppListener<Tenant>() {
                override fun onClickItem(item: Tenant, action: AppBaseAdapter.ItemAction) {
//                    navigateFragmentWithSlide(R.id.roomServiceFormFragment, args = Bundle().apply {
//                        putString(ServiceFormFragment.ITEM_KEY, Gson().toJson(item))
//                        putString(ServiceFormFragment.BOARDING_HOUSE_KEY, Gson().toJson(viewModel.liveData.currentBoardingHouse.value))
//                    })
                }
            }
        )
        views.btnCreateContract.setOnClickListener{
            navigateFragmentWithSlide(R.id.roomCreateContractFormFragment, args = Bundle().apply { putString(
                CreateContractFormFragment.ITEM_KEY, Gson().toJson(viewModel.liveData.currentRoom.value?.data)) })
        }

        views.rcvTenant.adapter = adapter
    }

    private lateinit var adapter: RoomTenantAdapter
    private fun listenStateViewModel() {
        viewModel.liveData.currentRoom.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                views.lyEmpty.isVisible = it.data?.contract == null
                views.rcvTenant.isVisible = it.data?.tenants?.isNotEmpty() == true
                adapter.updateCurrentRoom(it.data)
                adapter.updateData(it.data?.tenants ?: arrayListOf())
            }
        }
    }

}