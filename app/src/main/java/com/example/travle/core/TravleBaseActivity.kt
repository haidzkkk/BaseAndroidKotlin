package com.example.travle.core

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.travle.TravleApplication
import com.example.travle.di.DaggerTravleComponent
import com.example.travle.di.HasScreenInjector
import com.example.travle.di.TravleComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

abstract class TravleBaseActivity <VB : ViewBinding> : AppCompatActivity()
    , HasScreenInjector
{

    private lateinit var views: VB

    // scope trong pham vi này thôi
    private val appComponent: TravleComponent by lazy {
        DaggerTravleComponent.factory().create(this)
    }

    private lateinit var fragmentFactory: FragmentFactory
    private lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


        fragmentFactory = appComponent.fragmentFactory()
        viewModelFactory = appComponent.viewModelFactory()

        supportFragmentManager.fragmentFactory = fragmentFactory  //giúp quản lý vòng đời của Fragment trong khi khôi phục trạng thái của activity và đảm bảo rằng việc tái tạo Fragment diễn ra đúng cách  ||||  Việc xác định FragmentFactory trong supportFragmentManager giúp đảm bảo rằng FragmentManager sẽ sử dụng FragmentFactory đã cung cấp để tái tạo các Fragment, đảm bảo rằng các tham số của Fragment như arguments hay dữ liệu trạng thái được giữ nguyên đúng cách.

        views = getBinding()
        setContentView(views.root)
    }

    abstract fun getBinding(): VB



    @SuppressLint("CheckResult")
    protected fun <T : TravleViewEvents> TravleBaseViewModel<*, *, T>.observeViewEvents(observer: (T?) -> Unit) {
        viewEvents
            .observe()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                observer(it)
            }
    }

    protected fun <L: TravleViewLiveData> TravleBaseViewModel<L, *, *>.liveData() = liveData

    /**
     * use when create instance of fragment
     */
    protected fun createFragment(fragmentClass: Class<out Fragment>, args: Bundle?): Fragment {
        return fragmentFactory.instantiate(classLoader, fragmentClass.name).apply {
            arguments = args
        }
    }

    /**
     * use when create viewModel
     */
    protected val viewModelProvider
        get() = ViewModelProvider(this, viewModelFactory)


    override fun injector(): TravleComponent {
        return appComponent
    }

}