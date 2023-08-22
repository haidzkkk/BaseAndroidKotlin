package com.example.travle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.travle.R
import com.example.travle.TravleApplication
import com.example.travle.core.TravleBaseActivity
import com.example.travle.data.repository.HomeRepository
import com.example.travle.databinding.ActivityMainBinding
import com.example.travle.di.DaggerTravleComponent
import com.example.travle.di.TravleComponent
import com.example.travle.ui.Home.HomeViewEvent
import com.example.travle.ui.Home.HomeViewModel
import javax.inject.Inject

class MainActivity : TravleBaseActivity<ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mViewModel : HomeViewModel by lazy{
//        viewModelFactory
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
//        injector().inject(this) // scope bé trong base
        (application as TravleApplication).travleComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        mViewModel.observeViewEvents {
            when(it){
                is HomeViewEvent.ReturnTestViewEvent ->  Log.e("TAG", "Test View event")
                else -> {}
            }
        }

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        val navController = this.findNavController(R.id.fragment_view)
        views.navBottom.setupWithNavController(navController)
    }



}