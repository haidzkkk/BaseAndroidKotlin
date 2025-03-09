package com.app.langking.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.model.Account
import com.app.langking.databinding.FragmentSignupThirdBinding
import javax.inject.Inject

class SignupThirdFragment : AppBaseFragment<FragmentSignupThirdBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : AuthViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[AuthViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupThirdBinding {
        return FragmentSignupThirdBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        views.btnContinue.setOnClickListener {
            val password: String? = views.tilPassword.editText?.text?.toString()
            val confirmPassword: String? = views.tilConfirmPassword.editText?.text?.toString()
            val passwordCheck = password.isNullOrEmpty()
            val confirmPasswordCheck = confirmPassword.isNullOrEmpty()
            if( passwordCheck || confirmPasswordCheck){
                views.tilPassword.error = if(passwordCheck) "Hãy nhập mật khẩu" else null
                views.tilPassword.error = if(confirmPasswordCheck) "Hãy nhập lại mật khẩu" else null
                return@setOnClickListener
            }else if(password != confirmPassword){
                    views.tilConfirmPassword.error = "Mật khẩu không khớp"
                    return@setOnClickListener
            }
            views.tilPassword.error = null
            views.tilConfirmPassword.error = null

            val myAccount = (mViewModel.liveData.registerAccount ?: Account()).apply {
                this.password = password!!
            }

            mViewModel.handle(AuthViewAction.RegisterViewAction(myAccount))
        }
    }
}