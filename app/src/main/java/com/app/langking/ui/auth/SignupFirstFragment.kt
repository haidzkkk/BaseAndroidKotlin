package com.app.langking.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.model.Account
import com.app.langking.databinding.FragmentLoginBinding
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