package com.app.motel.feature.createContract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.service.DateConverter
import com.app.motel.common.ultis.popFragmentWithSlide
import com.app.motel.common.ultis.showToast
import com.app.motel.common.ultis.toStringMoney
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.model.Room
import com.app.motel.data.model.Status
import com.app.motel.data.model.Tenant
import com.app.motel.databinding.FragmentCreateContractFormBinding
import com.app.motel.feature.createContract.viewmodel.CreateContractViewModel
import com.google.gson.Gson
import java.util.Calendar
import javax.inject.Inject

class CreateContractFormFragment @Inject constructor() : AppBaseFragment<FragmentCreateContractFormBinding>() {
    companion object{
        const val ITEM_KEY = "room_item"
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCreateContractFormBinding {
        return FragmentCreateContractFormBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mViewModel : CreateContractViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(CreateContractViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

        init()
        listenerViewModelState()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        val item = Gson().fromJson(arguments?.getString(ITEM_KEY), Room::class.java)

        views.txtName.setText(item?.roomName ?: "")
        views.txtCreateDate.setText(DateConverter.getCurrentLocalDateTime())
        views.txtStartDate.setText(DateConverter.getCurrentLocalDateTime())
        views.txtEndDate.setText(DateConverter.dateToLocalString(Calendar.getInstance().apply {
            set(this.get(Calendar.YEAR) + 1, this.get(Calendar.MONTH), this.get(Calendar.DATE))
        }.time))
        views.txtDeposit.setText(item?.rentalPrice.toStringMoney())

        mViewModel.getTenantNotRented()

        views.btnSave.setOnClickListener{
            val currentTenantPosition = views.spinnerCustomer.selectedItemPosition
            val currentTenant: Tenant? = if(currentTenantPosition >= 0) mViewModel.liveData.tenantNotRented.value?.data?.get(currentTenantPosition)
                else null

            mViewModel.createContact(
                item,
                currentTenant,
                views.txtName.text.toString(),
                views.txtCreateDate.text.toString(),
                views.txtStartDate.text.toString(),
                views.txtEndDate.text.toString(),
                views.txtDeposit.text.toString().toStringMoney(),
                views.txtNote.text.toString(),
            )
        }
    }

    private fun listenerViewModelState() {
        mViewModel.liveData.tenantNotRented.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                val adapter = ArrayAdapter(
                    requireContext(), // Context
                    R.layout.item_spinner_text, // Layout
                    mViewModel.liveData.tenantNotRented.value?.data?.map { tenant: Tenant ->  tenant.fullName} ?: arrayListOf()
                )

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                views.spinnerCustomer.adapter = adapter
            }
        }
        mViewModel.liveData.createContract.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    activity?.showToast("Tao hợp đồng thành công")
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
                Status.ERROR -> {
                    activity?.showToast(it.message ?: "Có lỗi xảy ra")
                }
                else -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.clearStateCreate()
    }
}