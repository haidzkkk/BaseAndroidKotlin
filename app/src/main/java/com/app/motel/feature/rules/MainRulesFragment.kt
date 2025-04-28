package com.app.motel.feature.rules

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.ultis.navigateFragmentWithSlide
import com.app.motel.core.AppBaseAdapter
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.entity.PhongEntity
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.databinding.FragmentListRoomBinding
import com.app.motel.databinding.FragmentMainRulesBinding
import com.app.motel.feature.home.GeneralBoardingHouseFragment
import com.app.motel.feature.home.ManagementBoardingHouseFragment
import com.app.motel.feature.room.RoomAdapter
import com.app.motel.feature.room.RoomDetailFragment
import com.app.motel.feature.room.viewmodel.RoomViewModel
import com.app.motel.feature.rules.viewmodel.RulesViewModel
import com.app.motel.ui.adapter.ViewPagerAdapter
import com.app.motel.ui.custom.CustomTabBar
import com.google.gson.Gson
import javax.inject.Inject

class MainRulesFragment @Inject constructor() : AppBaseFragment<FragmentMainRulesBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainRulesBinding {
        return FragmentMainRulesBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : RulesViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(RulesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val fragments = arrayListOf(
            RulesContentFragment(),
            RuleFormFragment(),
        )

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

        views.tabBar.setOnTabSelectedListener(object: CustomTabBar.OnTabSelectedListener{
            override fun onTabSelected(position: Int) {
                views.viewPager.currentItem = position
            }
        })
    }


}