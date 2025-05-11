package com.history.vietnam.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.historicalEvent.viewmodel.HistoricalEventViewModel
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.app.motel.feature.page.viewmodel.PageViewModel
import com.history.vietnam.feature.Home.HomeViewModel
import com.history.vietnam.di.factory.AppViewModelFactory
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
    @ViewModelKey(HistoricalEventViewModel::class)
    fun bindHistoricalEventViewModel(viewmodel: HistoricalEventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoricalFigureViewModel::class)
    fun bindHistoricalFigureViewModel(viewmodel: HistoricalFigureViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PageViewModel::class)
    fun bindPageViewModel(viewmodel: PageViewModel): ViewModel

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