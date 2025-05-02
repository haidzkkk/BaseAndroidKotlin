package com.app.motel.feature.room

import android.os.Bundle
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
import com.app.motel.data.model.Tenant
import com.app.motel.databinding.FragmentRoomDetailTenantBinding
import com.app.motel.feature.createContract.CreateContractFormFragment
import com.app.motel.feature.room.viewmodel.RoomViewModel
import com.app.motel.feature.tenant.TenantAdapter
import com.app.motel.feature.tenant.TenantFormFragment
import com.app.motel.feature.tenant.TenantListAddRoomFragment
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
        adapter = TenantAdapter(
            object : AppBaseAdapter.AppListener<Tenant>() {
                override fun onClickItem(item: Tenant, action: AppBaseAdapter.ItemAction) {
                    val json = Gson().toJson(item)
                    navigateFragmentWithSlide(R.id.roomTenantFormFragment, args = Bundle().apply { putString(
                        TenantFormFragment.ITEM_KEY, json) })
                }
            }
        )

        views.btnCreateContract.setOnClickListener{
            navigateFragmentWithSlide(R.id.roomCreateContractFormFragment, args = Bundle().apply { putString(
                CreateContractFormFragment.ITEM_KEY, Gson().toJson(viewModel.liveData.currentRoom.value?.data)) })
        }
        views.btnAddTenant.setOnClickListener{
            navigateFragmentWithSlide(R.id.tenantListAddRoomFragment, args = Bundle().apply { putString(
                TenantListAddRoomFragment.ITEM_KEY, Gson().toJson(viewModel.liveData.currentRoom.value?.data)) })
        }

        views.rcvTenant.adapter = adapter
    }

    private lateinit var adapter: TenantAdapter
    private fun listenStateViewModel() {
        viewModel.liveData.currentRoom.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                views.lyEmpty.isVisible = it.data?.contract == null
                views.lyTenant.isVisible = it.data?.contract != null
                adapter.updateData(it.data?.tenants ?: arrayListOf())
                views.btnAddTenant.isVisible = it.data?.maxOccupants == null || it.data.maxOccupants > (it.data.tenants?.size ?: 0)
            }
        }
    }

}