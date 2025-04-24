package com.app.motel.feature.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.app.motel.AppApplication
import com.app.motel.core.AppBaseFragment
import com.app.motel.databinding.FragmentHomeBinding
import com.app.motel.feature.home.viewmodel.HomeViewModel
import com.app.motel.ui.adapter.ViewPagerAdapter
import com.app.motel.ui.custom.CustomTabBar
import javax.inject.Inject

class HomeFragment @Inject constructor() : AppBaseFragment<FragmentHomeBinding>() {

    val fragments = arrayListOf(
        ManagementBoardingHouseFragment(),
        GeneralBoardingHouseFragment(),
    )

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

//        viewModel.handle(HomeViewAction.GetMotelViewAction)
//        viewModel.handle(HomeViewAction.GetBoardingViewAction)

        setupUI()


        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupUI() {
        views.tabBar.setOnTabSelectedListener(object: CustomTabBar.OnTabSelectedListener{
            override fun onTabSelected(position: Int) {
                views.viewPager.currentItem = position
            }
        })

        val adapter = ViewPagerAdapter(
            fragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        views.viewPager.adapter = adapter
        views.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                views.tabBar.setTabSelected(position)
                views.viewPager.currentItem = position
            }
        })
    }

}