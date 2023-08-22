package com.example.travle.ui.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.preproject.utils.Status
import com.example.travle.R
import com.example.travle.TravleApplication
import com.example.travle.core.TravleBaseFragment
import com.example.travle.databinding.FragmentHomeBinding
import com.example.travle.di.TravleComponent
import com.example.travle.ui.MainActivity
import javax.inject.Inject

class HomeFragment @Inject constructor() : TravleBaseFragment<FragmentHomeBinding>() {

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