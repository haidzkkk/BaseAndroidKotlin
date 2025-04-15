package com.app.motel.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.di.modules.FragmentModule
import com.app.motel.di.modules.AppModule
import com.app.motel.di.modules.ViewModelModule
import com.app.motel.feature.Home.HomeFragment
import com.app.motel.feature.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ViewModelModule::class,
    FragmentModule::class,
    AppModule::class,
])
@Singleton
interface AppComponent {

    fun inject(appApplication: AppApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(homeFragment: HomeFragment)

    fun fragmentFactory(): FragmentFactory
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}