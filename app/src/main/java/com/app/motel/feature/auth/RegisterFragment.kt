package com.app.motel.feature.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.auth.viewmodel.AuthViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.databinding.FragmentLoginBinding
import com.history.vietnam.databinding.FragmentRegisterBinding
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.popFragmentWithSlide
import java.util.Locale
import javax.inject.Inject

class RegisterFragment : AppBaseFragment<FragmentRegisterBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater, container, false)
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
        views.btnBack.setOnClickListener{
            popFragmentWithSlide()
        }
        views.btnRegister.setOnClickListener {
            if(validateData()) {
                viewModel.register(
                    views.txtUsername.text.toString(),
                    views.txtPassword.text.toString(),
                )
            }

        }
    }
    private fun listenStateViewModel(){
        viewModel.liveData.register.observe(viewLifecycleOwner){
            when{
                it.isSuccess() -> {
                    requireActivity().showToast("Đăng ký thành công")
                    requireActivity().finish()
                }
                it.isError() -> {
                    requireActivity().showToast(it.message ?: "Đăng ký thất bại")
                }
            }
        }
    }

    private fun validateData(): Boolean{
        views.txtUsername.text.toString().apply {
            views.tilUsername.helperText = if (this.isEmpty()) "Tài khoản không được để trống" else null
        }
        views.txtPassword.text.toString().apply {
            views.tilPassword.helperText = when {
                this.isEmpty() -> "Tài khoản không được để trống"
                this.length < 8
                        || this == this.lowercase(Locale.getDefault())
                        || this.split("").mapNotNull { it.toIntOrNull() }.isEmpty()
                -> "Tài khoản phải có ít nhất 8 ký tự, trong đó phải có chữ viết thường, chữ hoa và chữ số"
                else -> null
            }
        }
        views.txtConfirmPassword.text.toString().apply {
            views.tilConfirmPassword.helperText = if (this != views.txtPassword.text.toString()) "Mật khẩu không khớp" else null
        }

        return views.tilUsername.helperText.isNullOrEmpty()
                && views.tilPassword.helperText.isNullOrEmpty()
                && views.tilConfirmPassword.helperText.isNullOrEmpty()
    }
}