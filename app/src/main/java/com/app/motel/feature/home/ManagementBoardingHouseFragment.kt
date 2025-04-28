package com.app.motel.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.ultis.startActivityWithSlide
import com.app.motel.core.AppBaseFragment
import com.app.motel.databinding.FragmentManagementBoardingHouseBinding
import com.app.motel.feature.createBill.CreateBillActivity
import com.app.motel.feature.createContract.CreateContractActivity
import com.app.motel.feature.handleBill.HandleBillActivity
import com.app.motel.feature.handleContract.ContractListActivity
import com.app.motel.feature.handleContract.HandleContractActivity
import com.app.motel.feature.home.viewmodel.HomeViewModel
import com.app.motel.feature.room.RoomActivity
import com.app.motel.feature.rules.RulesActivity
import com.app.motel.feature.service.ServiceActivity
import com.app.motel.feature.tenant.TenantActivity
import javax.inject.Inject

class ManagementBoardingHouseFragment @Inject constructor() : AppBaseFragment<FragmentManagementBoardingHouseBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mViewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentManagementBoardingHouseBinding {
        return FragmentManagementBoardingHouseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        setupAdminUI()
    }

    private fun setupAdminUI() {
        views.lyCreateContract.img.setImageResource(R.drawable.contract)
        views.lyCreateContract.title.text = "Tạo hợp đồng"
        views.lyCreateContract.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), CreateContractActivity::class.java))
        }

        views.lyService.img.setImageResource(R.drawable.bed)
        views.lyService.title.text = "Dịch vụ"
        views.lyService.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), ServiceActivity::class.java))
        }

        views.lyHandleContract.img.setImageResource(R.drawable.handle_contract)
        views.lyHandleContract.title.text = "Xử lý hợp đồng"
        views.lyHandleContract.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), HandleContractActivity::class.java))
        }

        views.lyCreateBill.img.setImageResource(R.drawable.create_bill)
        views.lyCreateBill.title.text = "Tạo hóa đơn"
        views.lyCreateBill.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), CreateBillActivity::class.java))
        }

        views.lyListRoom.img.setImageResource(R.drawable.room)
        views.lyListRoom.title.text = "Danh sách phòng thuê"
        views.lyListRoom.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), RoomActivity::class.java))
        }

        views.lyListTenant.img.setImageResource(R.drawable.tenant)
        views.lyListTenant.title.text = "Danh sách khách thuê"
        views.lyListTenant.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), TenantActivity::class.java))
        }

        views.lyListBill.img.setImageResource(R.drawable.payment)
        views.lyListBill.title.text = "Danh sách hóa đơn"
        views.lyListBill.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), HandleBillActivity::class.java))
        }

        views.lyListContract.img.setImageResource(R.drawable.list_contract)
        views.lyListContract.title.text = "Danh sách hợp đồng"
        views.lyListContract.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), ContractListActivity::class.java))
        }

        views.lyRule.img.setImageResource(R.drawable.icon_rule)
        views.lyRule.title.text = "Nội quy phòng trọ"
        views.lyRule.root.setOnClickListener{
            requireActivity().startActivityWithSlide(Intent(requireActivity(), RulesActivity::class.java))
        }

    }
}