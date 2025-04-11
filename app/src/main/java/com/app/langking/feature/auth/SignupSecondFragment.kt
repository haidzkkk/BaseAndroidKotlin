package com.app.langking.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.model.Account
import com.app.langking.databinding.FragmentSignupSecondBinding
import javax.inject.Inject

class SignupSecondFragment : AppBaseFragment<FragmentSignupSecondBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : AuthViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[AuthViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupSecondBinding {
        return FragmentSignupSecondBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        views.btnContinue.setOnClickListener {
            val email: String? = views.tilUsername.editText?.text?.toString()
            if(email.isNullOrEmpty()){
                views.tilUsername.error = "Hãy nhập tên đăng nhập"
                return@setOnClickListener
            }
            views.tilUsername.error = null

            val myAccount = (mViewModel.liveData.registerAccount ?: Account()).apply {
                this.email = email
            }

            mViewModel.handle(AuthViewAction.RegisterSetupViewAction(myAccount))
            mViewModel.handle(AuthViewAction.SignupForwardPageViewAction(2))
        }
    }

}