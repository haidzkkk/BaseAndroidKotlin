package com.app.motel.feature.auth

import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.auth.viewmodel.AuthViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentLoginBinding
import com.history.vietnam.ui.showLoadingDialog
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.navigateFragmentWithSlide
import javax.inject.Inject

class LoginFragment : AppBaseFragment<FragmentLoginBinding>() {
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
         return FragmentLoginBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : AuthViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(AuthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        init()
        listenStateViewModel()
    }

    private fun init(){
        val primaryColorSource = ContextCompat.getColor(requireActivity(), R.color.primary)
        val textColorSource = ContextCompat.getColor(requireActivity(), R.color.textColor1)
        val primaryColor = String.format("#%06X", 0xFFFFFF and primaryColorSource)
        val textColor = String.format("#%06X", 0xFFFFFF and textColorSource)
        views.tvInvite.text = Html.fromHtml(
            "<p color='$textColor'>Bạn cũng có thể tiếp tục với <u><font color='$primaryColor'>khách mời</font></u></p>",
            Html.FROM_HTML_MODE_LEGACY
        )

        views.btnLogin.setOnClickListener {
            viewModel.login(
                views.txtUsername.text.toString(),
                views.txtPassword.text.toString(),
            )
        }
        views.btnRegister.setOnClickListener {
            navigateFragmentWithSlide(R.id.registerFragment)
        }
        views.tvInvite.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun listenStateViewModel(){
        viewModel.liveData.login.observe(viewLifecycleOwner){
            when{
                it.isSuccess() -> {
                    requireActivity().finish()
                }
                it.isError() -> {
                    requireActivity().showToast(it.message ?: "Đăng ký thất bại")
                }
            }
        }
    }

}