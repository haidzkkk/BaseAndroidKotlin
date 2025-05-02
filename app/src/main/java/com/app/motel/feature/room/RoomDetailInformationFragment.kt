package com.app.motel.feature.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.ultis.navigateFragmentWithSlide
import com.app.motel.common.ultis.popFragmentWithSlide
import com.app.motel.common.ultis.showDialogConfirm
import com.app.motel.common.ultis.showToast
import com.app.motel.core.AppBaseAdapter
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.model.Room
import com.app.motel.data.model.Service
import com.app.motel.databinding.FragmentRoomDetailBinding
import com.app.motel.databinding.FragmentRoomDetailInformationBinding
import com.app.motel.feature.createBill.CreateBillFormFragment
import com.app.motel.feature.room.viewmodel.RoomViewModel
import com.app.motel.feature.service.ServiceFormFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import javax.inject.Inject

class RoomDetailInformationFragment @Inject constructor() : AppBaseFragment<FragmentRoomDetailInformationBinding>() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel : RoomViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(RoomViewModel::class.java)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRoomDetailInformationBinding {
        return FragmentRoomDetailInformationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

        super.onViewCreated(view, savedInstanceState)

        setEnableEdittext(views.txtCountService, false)
        setEnableEdittext(views.txtCurrentTenant, false)

        listenStateViewModel()
        adapterService = DetailRoomServiceAdapter(
            object : AppBaseAdapter.AppListener<Service>() {
                override fun onClickItem(item: Service, action: AppBaseAdapter.ItemAction) {
                    navigateFragmentWithSlide(R.id.roomServiceFormFragment, args = Bundle().apply {
                        putString(ServiceFormFragment.ITEM_KEY, Gson().toJson(item))
                    })
                }
            }
        )
        views.btnChangeService.setOnClickListener{
            navigateFragmentWithSlide(R.id.roomServiceFormFragment)
        }

        views.rcvService.adapter = adapterService
        views.btnUpdate.setOnClickListener {
            viewModel.updateRoom(
                viewModel.liveData.currentRoom.value?.data,
                views.txtNameRoom.text.toString(),
                views.txtArea.text.toString(),
                views.txtMaxTenant.text.toString(),
                views.txtPriceRoom.text.toString(),
            )
        }

        views.btnDelete.setOnClickListener{
            requireContext().showDialogConfirm(
                "Xóa phòng",
                "Bạn có chắc muốn xóa phòng ${viewModel.liveData.currentRoom.value?.data?.roomName} không ?",
                confirm = {
                    viewModel.deleteRoom(viewModel.liveData.currentRoom.value?.data)
                }
            )
        }
    }

    private lateinit var adapterService: DetailRoomServiceAdapter
    private fun listenStateViewModel() {
        viewModel.userController.state.currentBoardingHouse.observe(viewLifecycleOwner){
            if(it != null){
                views.tvNameBoardingHouse.text = it.data?.name
            }
        }
        viewModel.liveData.updateRoom.observe(viewLifecycleOwner){
             if(it.isSuccess()){
                activity?.showToast("Cập nhật phòng thành công")
            }else if(it.isError()){
                 activity?.showToast(it.message ?: "Có lỗi xảy ra")
            }
        }
        viewModel.liveData.deleteRoom.observe(viewLifecycleOwner){
             if(it.isSuccess()){
                 activity?.showToast("Xóa phòng thành công")
                 popFragmentWithSlide()
            }else if(it.isError()){
                 activity?.showToast(it.message ?: "Có lỗi xảy ra")
            }
        }
        viewModel.liveData.currentRoom.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                it.data?.also { currentRoom: Room? ->
                    views.txtNameRoom.setText(currentRoom?.roomName)
                    views.txtArea.setText(currentRoom?.area?.toString())
                    views.txtMaxTenant.setText(currentRoom?.maxOccupants?.toString())
                    views.txtCurrentTenant.setText(currentRoom?.tenants?.size?.toString())
                    views.txtPriceRoom.setText(currentRoom?.rentalPrice)
                    views.txtCountService.setText(currentRoom?.listService?.size?.toString())
                    adapterService.updateData(currentRoom?.listService ?: arrayListOf())
                    views.tvEmpty.isVisible = currentRoom?.listService.isNullOrEmpty()
                }
            }
        }
    }

    fun setEnableEdittext(txtView: TextInputEditText, enable: Boolean){
        txtView.isEnabled = enable
        txtView.backgroundTintList = resources.getColorStateList(if(enable) R.color.background1 else R.color.background3, requireContext().theme)
    }
}