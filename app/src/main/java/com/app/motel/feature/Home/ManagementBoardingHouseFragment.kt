package com.app.motel.feature.Home

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
import com.app.motel.feature.CreateContract.CreateContractActivity
import com.app.motel.feature.HandleContract.HandleContractActivity
import com.app.motel.feature.Service.ServiceActivity
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

         }

        views.lyListRoom.img.setImageResource(R.drawable.room)
        views.lyListRoom.title.text = "Danh sách phòng thuê"
        views.lyListRoom.root.setOnClickListener{

        }

        views.lyListTenant.img.setImageResource(R.drawable.tenant)
        views.lyListTenant.title.text = "Danh sách khách thuê"
        views.lyListTenant.root.setOnClickListener{

        }

        views.lyListBill.img.setImageResource(R.drawable.payment)
        views.lyListBill.title.text = "Danh sách hóa đơn"
        views.lyListBill.root.setOnClickListener{

        }

        views.lyListContract.img.setImageResource(R.drawable.list_contract)
        views.lyListContract.title.text = "Danh sách hợp đồng"
        views.lyListContract.root.setOnClickListener{

        }

    }
}