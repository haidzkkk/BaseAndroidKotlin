package com.history.vietnam.di.modules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.history.vietnam.di.factory.AppFragmentFactory
import com.history.vietnam.feature.Home.HomeFragment
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface FragmentModule {

    @Binds
    fun bindFragmentFactory(factory: AppFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    fun bindHomeFragment(homeFragment: HomeFragment): Fragment
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class FragmentKey(val value: KClass<out Fragment>)