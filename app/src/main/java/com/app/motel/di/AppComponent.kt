package com.history.vietnam.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.historicalFigure.HistoricalFigureActivity
import com.app.motel.feature.historicalFigure.HistoricalFigureFragment
import com.app.motel.feature.historicalFigure.HistoricalFigureTimelineFragment
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureCommentFragment
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureContentFragment
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureCustomBottomFragment
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureHomeFragment
import com.history.vietnam.AppApplication
import com.history.vietnam.di.modules.FragmentModule
import com.history.vietnam.di.modules.AppModule
import com.history.vietnam.di.modules.ViewModelModule
import com.history.vietnam.feature.Home.HomeFragment
import com.history.vietnam.feature.MainActivity
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
    fun inject(historicalFigureActivity: HistoricalFigureActivity)

    fun inject(fragment: HomeFragment)
    fun inject(fragment: HistoricalFigureFragment)
    fun inject(fragment: HistoricalFigureTimelineFragment)
    fun inject(fragment: HistoricalFigureHomeFragment)
    fun inject(fragment: HistoricalFigureCommentFragment)
    fun inject(fragment: HistoricalFigureContentFragment)
    fun inject(fragment: HistoricalFigureCustomBottomFragment)

    fun fragmentFactory(): FragmentFactory
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}