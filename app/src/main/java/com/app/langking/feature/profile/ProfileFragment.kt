package com.app.langking.feature.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.repository.UserRepository
import com.app.langking.databinding.FragmentProfileBinding
import com.app.langking.feature.auth.AuthActivity
import com.app.langking.ultis.popFragmentWithSlide
import com.app.langking.ultis.startActivityWithTransition
import javax.inject.Inject

class ProfileFragment : AppBaseFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var userRepo: UserRepository

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        val currentUser = userRepo.getCurrentUser()

        views.lvBack.setOnClickListener {
            popFragmentWithSlide()
        }
        views.tvName.text = if(currentUser.isLogin) currentUser.username else "Bạn chưa đăng nhập"
        views.tvLogout.text = if(currentUser.isLogin) "Đăng xuất" else "Đăng nhập"
        views.btnLogout.setOnClickListener {
            if(currentUser.isLogin){
                userRepo.logout()
            }
            popFragmentWithSlide()
            requireActivity().startActivityWithTransition(Intent(requireActivity(), AuthActivity::class.java))
        }
    }
}