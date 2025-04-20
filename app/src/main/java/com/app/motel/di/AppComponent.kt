package com.app.motel.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.di.modules.FragmentModule
import com.app.motel.di.modules.AppModule
import com.app.motel.di.modules.ViewModelModule
import com.app.motel.feature.BoardingHouse.CreateBoardingHouseFragment
import com.app.motel.feature.BoardingHouse.GreetingBoardingHouseFragment
import com.app.motel.feature.CreateContract.CreateContractFormFragment
import com.app.motel.feature.CreateContract.CreateContractActivity
import com.app.motel.feature.CreateContract.CreateContractListFragment
import com.app.motel.feature.Home.GeneralBoardingHouseFragment
import com.app.motel.feature.Home.HomeFragment
import com.app.motel.feature.Home.ManagementBoardingHouseFragment
import com.app.motel.feature.MainActivity
import com.app.motel.feature.Service.ServiceActivity
import com.app.motel.feature.Service.ServiceFormFragment
import com.app.motel.feature.Service.ServiceListFragment
import com.app.motel.feature.auth.AuthActivity
import com.app.motel.feature.auth.LoginFragment
import com.app.motel.feature.auth.RegisterFragment
import com.app.motel.feature.news.NewsFragment
import com.app.motel.feature.notify.NotifyFragment
import com.app.motel.feature.profile.ProfileFragment
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
    fun inject(authActivity: AuthActivity)
    fun inject(createContractActivity: CreateContractActivity)
    fun inject(serviceActivity: ServiceActivity)

    fun inject(homeFragment: HomeFragment)
    fun inject(newsFragment: NewsFragment)
    fun inject(notifyFragment: NotifyFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(managementBoardingHouseFragment: ManagementBoardingHouseFragment)
    fun inject(generalBoardingHouseFragment: GeneralBoardingHouseFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(registerFragment: RegisterFragment)
    fun inject(greetingBoardingHouseFragment: GreetingBoardingHouseFragment)
    fun inject(createBoardingHouseFragment: CreateBoardingHouseFragment)
    fun inject(createContractListFragment: CreateContractListFragment)
    fun inject(createContractFormFragment: CreateContractFormFragment)
    fun inject(serviceListFragment: ServiceListFragment)
    fun inject(serviceFormFragment: ServiceFormFragment)

    fun fragmentFactory(): FragmentFactory
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}