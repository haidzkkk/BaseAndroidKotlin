package com.example.travle.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travle.ui.Home.HomeViewModel
import com.example.travle.di.factory.TravleViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindSearchViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    fun bindHomeViewModelFactory(factory: TravleViewModelFactory): ViewModelProvider.Factory

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