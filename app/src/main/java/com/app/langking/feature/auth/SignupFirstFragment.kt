package com.app.langking.feature.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.model.Account
import com.app.langking.databinding.FragmentSignupFirstBinding
import javax.inject.Inject

class SignupFirstFragment : AppBaseFragment<FragmentSignupFirstBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : AuthViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[AuthViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupFirstBinding {
        return FragmentSignupFirstBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        views.btnContinue.setOnClickListener {
            val username: String? = views.tilName.editText?.text?.toString()
            if(username.isNullOrEmpty()){
                views.tilName.error = "Hãy nhập tên của bạn"
                return@setOnClickListener
            }
            views.tilName.error = null

            val myAccount = Account(username = username)

            mViewModel.handle(AuthViewAction.RegisterSetupViewAction(myAccount))
            mViewModel.handle(AuthViewAction.SignupForwardPageViewAction(1))
        }

    }

}