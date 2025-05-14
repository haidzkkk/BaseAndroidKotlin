package com.history.vietnam.feature.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.auth.AuthActivity
import com.app.motel.feature.auth.viewmodel.AuthViewModel
import com.app.motel.feature.profile.UserController
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentLoginBinding
import com.history.vietnam.databinding.FragmentProfileBinding
import com.history.vietnam.ui.showDialogConfirm
import com.history.vietnam.ultis.navigateFragmentWithSlide
import javax.inject.Inject

class ProfileFragment : AppBaseFragment<FragmentProfileBinding>() {
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var userController: UserController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun init(){
        views.itemInfo.apply {
            startIcon.setImageResource(R.drawable.icon_info)
            label.text = "Thông tin tài khoản"
            root.setOnClickListener {
                navigateFragmentWithSlide(R.id.informationFragment)
            }
        }
        views.itemSaved.apply {
            startIcon.setImageResource(R.drawable.icon_save)
            label.text = "Đã lưu"
            root.setOnClickListener {

            }
        }
        views.itemPolicy.apply {
            startIcon.setImageResource(R.drawable.icon_lock)
            label.text = "Chính sách và điều khoản"
            root.setOnClickListener {

            }
        }
        views.itemCommunity.apply {
            startIcon.setImageResource(R.drawable.icon_instagram)
            label.text = "Instagram"
            root.setOnClickListener {

            }
        }
        views.itemSetting.apply {
            startIcon.setImageResource(R.drawable.icon_setting)
            label.text = "Cài đặt"
            root.setOnClickListener {

            }
        }
        views.itemLogout.apply {
            startIcon.setImageResource(R.drawable.icon_logout)
            label.text = "Đăng xuất tài khoản"
            root.setOnClickListener {
                if(userController.state.currentUser.value?.data == null){
                    userController.logout()
                    requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                    return@setOnClickListener
                }
                requireActivity().showDialogConfirm(
                    title = "Đăng xuất",
                    content = "Bạn có chắc muốn đăng xuất tài khoản này?",
                    buttonConfirm = "Đăng xuất",
                    buttonCancel = "Hủy",
                    confirm = {
                        userController.logout()
                        requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                    },
                )
            }
        }
    }

    private fun listenStateViewModel(){
        userController.state.currentUser.observe(viewLifecycleOwner){
            views.itemInfo.root.isVisible = it.data != null
            views.itemLogout.label.text = if(it.data != null) "Đăng xuất tài khoản" else "Đăng nhập"
            views.tvWelcome.text = it.data.let { user ->
                "Xin chào${if(user != null) ", ${user.getUserName}" else ""}"
            }
        }
    }
}