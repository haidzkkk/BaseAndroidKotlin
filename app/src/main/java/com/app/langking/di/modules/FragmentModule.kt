package com.app.langking.di.modules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.app.langking.di.factory.TravleFragmentFactory
import com.app.langking.ui.Home.HomeFragment
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface FragmentModule {

    @Binds
    fun bindFragmentFactory(factory: TravleFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    fun bindHomeFragment(homeFragment: HomeFragment): Fragment
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class FragmentKey(val value: KClass<out Fragment>)