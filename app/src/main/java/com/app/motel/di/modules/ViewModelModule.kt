package com.app.motel.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.home.viewmodel.HomeViewModel
import com.app.motel.di.factory.AppViewModelFactory
import com.app.motel.feature.boardingHouse.viewmodel.BoardingHouseViewModel
import com.app.motel.feature.createContract.viewmodel.CreateContractViewModel
import com.app.motel.feature.handleContract.viewmodel.HandleContractViewModel
import com.app.motel.feature.service.viewmodel.ServiceViewModel
import com.app.motel.feature.auth.viewmodel.AuthViewModel
import com.app.motel.feature.createBill.viewmodel.CreateBillViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {

    @Binds
    fun bindHomeViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateContractViewModel::class)
    fun bindCreateContractViewModel(authViewModel: CreateContractViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HandleContractViewModel::class)
    fun bindHandleContractViewModel(authViewModel: HandleContractViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ServiceViewModel::class)
    fun bindServiceViewModel(authViewModel: ServiceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateBillViewModel::class)
    fun bindCreateBillViewModel(authViewModel: CreateBillViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BoardingHouseViewModel::class)
    fun bindBoardingHouseViewModel(boardingHouseViewModel: BoardingHouseViewModel): ViewModel

}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)