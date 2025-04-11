package com.app.langking.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentLoginBinding
import com.app.langking.ultis.finishActivityWithTransition
import javax.inject.Inject

class LoginFragment : AppBaseFragment<FragmentLoginBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : AuthViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[AuthViewModel::class.java]
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        views.btnSignin.setOnClickListener{
            val email: String = views.txtEmail.text.toString()
            val password: String = views.txtPassword.text.toString()
            val emailCheck = email.isEmpty()
            val passwordCheck = password.isEmpty()
            if(emailCheck || passwordCheck){
                views.tilEmail.error = if(emailCheck) "Hãy nhập email" else null
                views.tilPassword.error = if(passwordCheck) "Hãy nhập mật khẩu" else null
                return@setOnClickListener
            }
            views.txtEmail.error = null
            views.txtPassword.error = null

            mViewModel.handle(AuthViewAction.LoginViewAction(views.txtEmail.text.toString(), views.txtPassword.text.toString()))
        }

        views.icBack.setOnClickListener{
            requireActivity().finishActivityWithTransition()
        }

        views.btnSignup.setOnClickListener{
            findNavController().navigate(R.id.signupFragment)
        }

    }


}