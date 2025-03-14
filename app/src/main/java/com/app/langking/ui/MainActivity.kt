package com.app.langking.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseActivity
import com.app.langking.databinding.ActivityMainBinding
import com.app.langking.ui.Home.HomeViewAction
import com.app.langking.ui.Home.HomeViewEvent
import com.app.langking.ui.Home.HomeViewModel
import javax.inject.Inject

class MainActivity : AppBaseActivity<ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel : HomeViewModel by lazy{
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TravleApplication).travleComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        viewModel.observeViewEvents {
            when(it){
                is HomeViewEvent.ReturnTestViewEvent ->  Log.e("TAG", "Test View event")
                else -> {}
            }
        }

        setUpBottomNav()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handle(HomeViewAction.updateViewAction)
    }

    private fun setUpBottomNav() {
        val navController = this.findNavController(R.id.fragment_view)
        views.navBottom.setupWithNavController(navController)
    }



}