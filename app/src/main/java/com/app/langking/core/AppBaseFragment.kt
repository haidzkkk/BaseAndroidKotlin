package com.app.langking.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.app.langking.di.DaggerTravleComponent
import com.app.langking.di.HasScreenInjector
import com.app.langking.di.TravleComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AppBaseFragment <VB: ViewBinding> : Fragment(), HasScreenInjector
{

    protected val nimpeBaseActivity: AppBaseActivity<*> by lazy {
        activity as AppBaseActivity<*>
    }

    private lateinit var screenComponent: TravleComponent

    private var _binding: VB? = null
    protected val views: VB get() = _binding!!

    override fun onAttach(context: Context) {
        screenComponent = DaggerTravleComponent.factory().create(context)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getBinding(inflater, container)
        return views.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        uiDisposables.clear()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        uiDisposables.dispose()
    }

/* ==========================================================================================
 * Disposable
 * ========================================================================================== */

    private val uiDisposables = CompositeDisposable()

    protected fun Disposable.disposeOnDestroyView() {
        uiDisposables.add(this)
    }

    protected fun <T : AppViewEvent> AppBaseViewModel<*, *, T>.observeViewEvents(observer: (T) -> Unit) {
        viewEvents
            .observe()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                observer(it)
            }
            .disposeOnDestroyView()
    }



    override fun injector(): TravleComponent {
        return screenComponent
    }

    fun inValidate(){
    }

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB

}