package com.app.motel.di.factory

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class AppFragmentFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out Fragment>, Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator: Provider<out Fragment>? = creators[fragmentClass]
        return super.instantiate(classLoader, className)
        return if (creator == null) {
            Log.e("MotelFragmentFactory","Unknown model class: $className, fallback to default instance")
            super.instantiate(classLoader, className)
        } else {
            creator.get()
        }
    }

}