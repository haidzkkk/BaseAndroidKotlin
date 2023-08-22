package com.example.travle.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.travle.TravleApplication
import com.example.travle.core.TravleBaseActivity
import com.example.travle.di.modules.FragmentModule
import com.example.travle.di.modules.NetworkModule
import com.example.travle.di.modules.ViewModelModule
import com.example.travle.ui.Home.HomeFragment
import com.example.travle.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ViewModelModule::class,
    NetworkModule::class,
    FragmentModule::class
])
@Singleton
interface TravleComponent {

    fun inject(travleApplication: TravleApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)

//    fun inject(fragment: HomeFragment)

    fun fragmentFactory(): FragmentFactory
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): TravleComponent
    }
}