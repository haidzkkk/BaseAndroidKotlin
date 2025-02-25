package com.app.langking.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.app.langking.TravleApplication
import com.app.langking.di.modules.FragmentModule
import com.app.langking.di.modules.NetworkModule
import com.app.langking.di.modules.ViewModelModule
import com.app.langking.ui.Home.HomeFragment
import com.app.langking.ui.MainActivity
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