package com.app.langking.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.app.langking.TravleApplication
import com.app.langking.di.modules.FragmentModule
import com.app.langking.di.modules.NetworkModule
import com.app.langking.di.modules.ViewModelModule
import com.app.langking.feature.Home.HomeFragment
import com.app.langking.feature.Learn.ExerciseFragment
import com.app.langking.feature.Learn.LearnActivity
import com.app.langking.feature.Learn.LessonWordFragment
import com.app.langking.feature.Learn.OverviewFragment
import com.app.langking.feature.MainActivity
import com.app.langking.feature.auth.AuthActivity
import com.app.langking.feature.auth.CongurrationsFragment
import com.app.langking.feature.auth.LoginFragment
import com.app.langking.feature.auth.SignupFirstFragment
import com.app.langking.feature.auth.SignupFragment
import com.app.langking.feature.auth.SignupSecondFragment
import com.app.langking.feature.auth.SignupThirdFragment
import com.app.langking.feature.inbox.InboxFragment
import com.app.langking.feature.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ViewModelModule::class,
    NetworkModule::class,
    FragmentModule::class
])
@Singleton
interface TravleComponent {

    fun inject(travleApplication: TravleApplication)

    fun inject(mainActivity: MainActivity)
    fun inject(authActivity: AuthActivity)
    fun inject(learnActivity: LearnActivity)

    fun inject(homeFragment: HomeFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(signupFragment: SignupFragment)
    fun inject(signupFirstFragment: SignupFirstFragment)
    fun inject(signupSecondFragment: SignupSecondFragment)
    fun inject(signupSecondFragment: SignupThirdFragment)
    fun inject(signupSecondFragment: CongurrationsFragment)
    fun inject(overviewFragment: OverviewFragment)
    fun inject(lessonWordFragment: LessonWordFragment)
    fun inject(exerciseFragment: ExerciseFragment)
    fun inject(inboxFragment: InboxFragment)
    fun inject(profileFragment: ProfileFragment)

//    fun inject(fragment: HomeFragment)

    fun fragmentFactory(): FragmentFactory
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): TravleComponent
    }
}