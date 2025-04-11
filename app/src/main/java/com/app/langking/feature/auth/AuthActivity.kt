package com.app.langking.feature.auth

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseActivity
import com.app.langking.databinding.ActivityAuthBinding
import com.app.langking.ultis.Status
import com.app.langking.ultis.showLoadingDialog
import javax.inject.Inject

class AuthActivity : AppBaseActivity<ActivityAuthBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mViewModel : AuthViewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

    var loadingDialog: Dialog? = null

    override fun getBinding(): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TravleApplication).travleComponent.inject(this)
        super.onCreate(savedInstanceState)
        listenState()
    }

    private fun listenState() {
        mViewModel.liveData.loginResult.observe(this){ account ->
            loadingDialog?.dismiss()
            loadingDialog = null
            when(account.status){
                Status.LOADING -> {
                    loadingDialog = showLoadingDialog(this, layoutInflater, "Đang đăng nhập")
                }
                Status.SUCCESS -> {
                    Toast.makeText(this, "Đăng nhập thành công! Chào ${account.data?.username}", Toast.LENGTH_SHORT).show()
                    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
                    val navController = navHostFragment.navController
                    navController.navigate(R.id.congurrationsFragment)
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show()

                }
            }
        }

        mViewModel.liveData.registerResult.observe(this){ account ->
            loadingDialog?.dismiss()
            loadingDialog = null
            when(account.status){
                Status.LOADING -> {
                    loadingDialog = showLoadingDialog(this, layoutInflater, "Đang đăng ký")
                }
                Status.SUCCESS -> {
                    Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
                    val navController = navHostFragment.navController
                    navController.navigate(R.id.congurrationsFragment)
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Đăng ký thất bại ${account.message}", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}