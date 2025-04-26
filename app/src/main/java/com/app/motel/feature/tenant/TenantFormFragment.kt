package com.app.motel.feature.tenant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.common.service.DateConverter
import com.app.motel.common.ultis.popFragmentWithSlide
import com.app.motel.common.ultis.setOnEndDrawableClick
import com.app.motel.common.ultis.showToast
import com.app.motel.core.AppBaseDialog
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.entity.NguoiThueEntity
import com.app.motel.data.model.Tenant
import com.app.motel.databinding.DialogDatePickerBinding
import com.app.motel.databinding.FragmentTenantFormBinding
import com.app.motel.feature.tenant.viewmodel.TenantViewModel
import com.google.gson.Gson
import java.util.Calendar
import javax.inject.Inject

class TenantFormFragment : AppBaseFragment<FragmentTenantFormBinding>() {

    companion object {
        val ITEM_KEY: String = "TENANT_KEY"
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTenantFormBinding {
        return FragmentTenantFormBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TenantViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TenantViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        listenDataViewModel()

        val tenant: Tenant? = Gson().fromJson(arguments?.getString(ITEM_KEY), Tenant::class.java)
        viewModel.initForm(tenant)

        views.txtBirthDay.setOnEndDrawableClick {
            showDialogBirdDay()
        }
        views.btnAdd.setOnClickListener {
            viewModel.handleTenant(
                tenant = viewModel.liveData.currentTenant.value,
                fullName = views.txtFullName.text.toString(),
                state = views.txtTypeRent.text.toString(),
                phoneNumber = views.txtPhone.text.toString(),
                birthDay = views.txtBirthDay.text.toString(),
                idCard = views.txtCcdc.text.toString(),
                homeTown = views.txtHomeTown.text.toString(),
                username = views.txtUsername.text.toString(),
                password = views.txtPassword.text.toString(),
            )
        }
        views.btnUpdate.setOnClickListener {
            viewModel.handleTenant(
                tenant = viewModel.liveData.currentTenant.value,
                fullName = views.txtFullName.text.toString(),
                state = views.txtTypeRent.text.toString(),
                phoneNumber = views.txtPhone.text.toString(),
                birthDay = views.txtBirthDay.text.toString(),
                idCard = views.txtCcdc.text.toString(),
                homeTown = views.txtHomeTown.text.toString(),
                username = views.txtUsername.text.toString(),
                password = views.txtPassword.text.toString(),
            )
        }
        views.btnCancel.setOnClickListener {
            popFragmentWithSlide()
        }
        views.btnLock.setOnClickListener {
            val isLock: Boolean = viewModel.liveData.currentTenant.value?.isLock ?: false
            viewModel.changeStateTenant(
                viewModel.liveData.currentTenant.value,
                !isLock
            )
        }
    }

    private fun listenDataViewModel() {
        viewModel.liveData.currentTenant.observe(viewLifecycleOwner){
            views.lyAdd.isVisible = it == null
            views.lyUpdate.isVisible = it != null
            views.tvTitle.text = if(it == null) "Thêm người thuê mới" else "Sửa người thuê"

            if(it != null){
                views.apply {
                    txtFullName.setText(it.fullName)
                    txtTypeRent.setText(it.status)
                    txtPhone.setText(it.phoneNumber)
                    txtBirthDay.setText(it.birthDay)
                    txtCcdc.setText(it.idCard)
                    txtHomeTown.setText(it.homeTown)
                    txtUsername.setText(it.username)
                    txtPassword.setText(it.password)
                    btnLockText.text = if(it.isLock) "Mở khóa" else "Khóa"
                }
            }else{
                views.apply {
                txtTypeRent.setText(NguoiThueEntity.Status.INACTIVE.value)
                }
            }
        }
        viewModel.liveData.handleTenant.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                 requireActivity().showToast(it.message ?: "Thành công")
                 popFragmentWithSlide()
            }else if (it.isError()){
                requireActivity().showToast(it.message ?: "Có lỗi xảy ra")
            }
        }
    }

    private fun showDialogBirdDay(){
        val dialog = AppBaseDialog.Builder(requireContext(), DialogDatePickerBinding.inflate(layoutInflater))
            .build()
        dialog.show()

        dialog.setOnDismissListener {

        }

        val calendar: Calendar = Calendar.getInstance().apply {
            time = DateConverter.localStringToDate(views.txtBirthDay.text.toString())
                ?: DateConverter.localStringToDate("1/1/2000")
                ?: DateConverter.getCurrentDateTime()
        }

        dialog.binding.datePickerDob.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(
            Calendar.DAY_OF_MONTH)
        ) { view, year, monthOfYear, dayOfMonth ->
            calendar.set(year, monthOfYear, dayOfMonth)
            views.txtBirthDay.setText(DateConverter.dateToLocalString(calendar.time))
        }
    }
}