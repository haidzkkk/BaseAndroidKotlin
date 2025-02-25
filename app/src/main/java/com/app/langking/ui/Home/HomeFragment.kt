package com.app.langking.ui.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.langking.ultis.Status
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment @Inject constructor() : AppBaseFragment<FragmentHomeBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mViewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)

        // oke bat dau
        views.text2.text = "hello home"

        mViewModel.handleRetrunTest()
        mViewModel.handle(HomeViewAction.getTravleViewAction)

        mViewModel.liveData.apply {
            this.travlesLiveData.observe(requireActivity()) {
                when(it.status){
                    Status.SUCCESS ->{
                        Log.e("TAG", "frg liveData: ${it.data}", )
                    }
                    else -> {}
                }
            }

            travTest.observe(requireActivity()){
                Log.e("TAG", "test viewModel: ${it}", )
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

}