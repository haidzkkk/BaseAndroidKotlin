package com.history.vietnam.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.historicalEvent.HistoricalEventActivity
import com.app.motel.feature.historicalEvent.HistoricalEventTimeLineFragment
import com.app.motel.feature.historicalFigure.HistoricalFigureActivity
import com.app.motel.feature.page.PageFragment
import com.app.motel.feature.historicalFigure.HistoricalFigureTimelineFragment
import com.app.motel.feature.page.CommentFragment
import com.app.motel.feature.page.IndexFragment
import com.app.motel.feature.page.SettingBottomFragment
import com.app.motel.feature.page.PageHomeFragment
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

    fun inject(activity: MainActivity)
    fun inject(activity: HistoricalFigureActivity)
    fun inject(activity: HistoricalEventActivity)

    fun inject(fragment: HomeFragment)
    fun inject(fragment: PageFragment)
    fun inject(fragment: HistoricalFigureTimelineFragment)
    fun inject(fragment: PageHomeFragment)
    fun inject(fragment: CommentFragment)
    fun inject(fragment: IndexFragment)
    fun inject(fragment: SettingBottomFragment)
    fun inject(fragment: HistoricalEventTimeLineFragment)

    fun fragmentFactory(): FragmentFactory
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}