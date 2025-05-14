package com.app.motel.feature.profile

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.User
import com.history.vietnam.databinding.FragmentInformationBinding
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class InformationFragment : AppBaseFragment<FragmentInformationBinding>() {
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInformationBinding {
        return FragmentInformationBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var userController: UserController

    private var editAccount = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    private fun init(){
        switchVisibleLayout()
        views.tvAction.setOnClickListener {
            if(!editAccount){
                switchVisibleLayout()
                return@setOnClickListener
            }
            userController.updateInformation(
                name = views.tilName.editText?.text.toString(),
                email = views.tilEmail.editText?.text.toString(),
                numberPhone = views.tilNumberPhone.editText?.text.toString()
            )
        }

        views.btnBack.setOnClickListener{
            popFragmentWithSlide()
        }
    }

    private fun listenStateViewModel(){
        userController.state.currentUser.observe(viewLifecycleOwner){
            fillData(it.data)
        }

        userController.state.updateCurrentUser.observe(viewLifecycleOwner){
            when{
                it.isSuccess() -> {
                    requireActivity().showToast("Cập nhật thông tin thành công")
                    fillData(it.data)
                    switchVisibleLayout()
                }
                it.isError() -> {
                    requireActivity().showToast(it.message ?: "Có lỗi xảy ra")
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillData(user: User?){
        views.tvName.apply {
          if(user?.name?.isNotEmpty() == true ) {
              setTypeface(null, Typeface.NORMAL)
              text = user.name
          } else {
              setTypeface(null, Typeface.ITALIC)
              text = "Không có thông tin"
          }
        }
        views.tvEmail.apply {
          if(user?.email?.isNotEmpty() == true ) {
              setTypeface(null, Typeface.NORMAL)
              text = user.email
              paint.isUnderlineText = true
          } else {
              paint.isUnderlineText = false
              setTypeface(null, Typeface.ITALIC)
              text = "Không có thông tin"
          }
        }
        views.tvNumberPhone.apply {
          if(user?.numberPhone?.isNotEmpty() == true ) {
              setTypeface(null, Typeface.NORMAL)
              text = user.numberPhone
          } else {
              setTypeface(null, Typeface.ITALIC)
              text = "Không có thông tin"
          }
        }
        views.tilName.editText?.setText(user?.name ?: "")
        views.tilEmail.editText?.setText(user?.email ?: "")
        views.tilNumberPhone.editText?.setText(user?.numberPhone ?: "")
    }

    private fun switchVisibleLayout(){
        editAccount = !editAccount

        views.tvName.isVisible = !editAccount
        views.tvEmail.isVisible = !editAccount
        views.tvNumberPhone.isVisible = !editAccount

        views.tilName.isVisible = editAccount
        views.tilEmail.isVisible = editAccount
        views.tilNumberPhone.isVisible = editAccount

        views.tvAction.text = if(editAccount) "Lưu" else "Sửa"
    }

    override fun onDestroy() {
        super.onDestroy()
        userController.clearData()
    }
}