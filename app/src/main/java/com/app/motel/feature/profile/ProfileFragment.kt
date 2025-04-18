package com.app.motel.feature.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.motel.AppApplication
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.model.Status
import com.app.motel.databinding.FragmentProfileBinding
import com.app.motel.feature.auth.AuthActivity
import javax.inject.Inject

class ProfileFragment @Inject constructor() : AppBaseFragment<FragmentProfileBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var profileViewModel : ProfileController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        views.tvContent.setOnClickListener {
            requireActivity().apply {
                profileViewModel.logout()
                finishAffinity()
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }

        profileViewModel.state.currentUser.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    val user = it.data!!
                    views.tvContent.text = "${user.id} ${user.name} ${user.role}"
                }
                else -> {}
            }
        }
    }
}