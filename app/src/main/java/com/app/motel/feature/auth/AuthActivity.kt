package com.app.motel.feature.auth

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.motel.feature.auth.viewmodel.AuthViewModel
import com.app.motel.feature.profile.UserController
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.databinding.ActivityAuthBinding
import com.history.vietnam.ui.showLoadingDialog
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class AuthActivity : AppBaseActivity<ActivityAuthBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : AuthViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
    }

    @Inject
    lateinit var userController: UserController

    override fun getBinding(): ActivityAuthBinding {
        return ActivityAuthBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        init()
        listenStateViewModel()
    }

    private fun init(){
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackWithAnimation()
            }
        })
    }

    private fun handleBackWithAnimation() {
        val navController = findNavController(R.id.fragment_view)

        if (navController.currentDestination?.id == R.id.loginFragment) {
            finish()
        } else {
            popFragmentWithSlide(R.id.fragment_view)
        }
    }

    private var dialogLoading: Dialog? = null
    fun listenStateViewModel(){
        viewModel.liveData.login.observe(this){
            if(it.isLoading()){
                dialogLoading = showLoadingDialog(this, layoutInflater)
            }else{
                dialogLoading?.dismiss()
                dialogLoading = null
            }
        }
        viewModel.liveData.register.observe(this){
            if(it.isLoading()){
                dialogLoading = showLoadingDialog(this, layoutInflater)
            }else{
                dialogLoading?.dismiss()
                dialogLoading = null
            }
        }
    }
}