package com.app.motel.feature.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.motel.feature.profile.UserController
import com.history.vietnam.R
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.databinding.ActivityMainBinding
import com.history.vietnam.feature.Home.HomeViewEvent
import com.history.vietnam.feature.Home.HomeViewModel
import javax.inject.Inject

class MainActivity : AppBaseActivity<ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : HomeViewModel by lazy{
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    @Inject
    lateinit var userController: UserController

    override fun onResume() {
        super.onResume()
        userController.getCurrentUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        mViewModel.observeViewEvents {
            when(it){
                is HomeViewEvent.ReturnTestViewEvent ->  Log.e("TAG", "Test View event")
                else -> {}
            }
        }
        Handler(Looper.getMainLooper()).post {
            setUpBottomNav()
        }
    }

    private fun setUpBottomNav() {
        val navController = this.findNavController(R.id.fragment_view)
        views.navBottom.setupWithNavController(navController)

        views.navBottom.menu.clear()
        views.navBottom.inflateMenu(R.menu.menu_bottom)
        this.findNavController(R.id.fragment_view).currentDestination?.id?.apply {
            views.navBottom.selectedItemId = this
        }
        this.findNavController(R.id.fragment_view).addOnDestinationChangedListener { controller, destination, arguments ->
            destination.id.let { currentLayoutId ->
                views.navBottom.isVisible = currentLayoutId == R.id.nav_home || currentLayoutId == R.id.nav_profile || currentLayoutId == R.id.nav_inbox
            }
        }
    }
}