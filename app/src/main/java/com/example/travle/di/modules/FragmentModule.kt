package com.example.travle.di.modules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.travle.di.factory.TravleFragmentFactory
import com.example.travle.ui.Home.HomeFragment
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