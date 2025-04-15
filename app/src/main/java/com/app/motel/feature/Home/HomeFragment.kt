package com.app.motel.feature.Home

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.model.Status
import com.app.motel.databinding.FragmentHomeBinding
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
        (requireActivity().application as AppApplication).appComponent.inject(this)

        mViewModel.sendEventTest()
        mViewModel.handle(HomeViewAction.getMotelViewAction)

        mViewModel.liveData.apply {
            this.motelsLiveData.observe(requireActivity()) {
                when(it.status){
                    Status.SUCCESS ->{
                        Log.e("TAG", "frg liveData: ${it.data}", )

                        val adapter = ArrayAdapter(
                            requireContext(),
                            R.layout.simple_list_item_1,
                            it.data?.map { item -> item.name } ?: arrayListOf()
                        )

                        views.listView.adapter = adapter
                        adapter.notifyDataSetChanged()

                    }
                    else -> {}
                }
            }

            testString.observe(requireActivity()){
                Log.e("TAG", "test viewModel: ${it}", )
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

}