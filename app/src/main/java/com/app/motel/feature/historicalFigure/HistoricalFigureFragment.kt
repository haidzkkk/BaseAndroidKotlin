package com.app.motel.feature.historicalFigure

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.feature.historicalFigure.adapter.DynastyAdapter
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureCommentFragment
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureContentFragment
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureCustomBottomFragment
import com.app.motel.feature.historicalFigure.menu.HistoricalFigureHomeFragment
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.app.motel.ui.adapter.ViewPagerAdapter
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentHistoricalFigureBinding
import com.history.vietnam.databinding.FragmentHistoricalFigureTimelineBinding
import javax.inject.Inject

class HistoricalFigureFragment : AppBaseFragment<FragmentHistoricalFigureBinding>() {

    companion object{
        const val KEY_ITEM_FIGURE = "KEY_ITEM_FIGURE"
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistoricalFigureBinding {
        return FragmentHistoricalFigureBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HistoricalFigureViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HistoricalFigureViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData

        init()
        setUpBottomNav()
        listenStateViewModel()
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private fun init() {
        val figure: HistoricalFigure? = arguments?.getString(KEY_ITEM_FIGURE)?.let {
            Gson().fromJson(it, HistoricalFigure::class.java)
        }
        viewModel.initFigure(figure)

        val fragments = arrayListOf(
            HistoricalFigureHomeFragment(),
            HistoricalFigureCommentFragment(),
            HistoricalFigureContentFragment()
        )
        viewPagerAdapter = ViewPagerAdapter(fragments, childFragmentManager, lifecycle)
        views.viewPager.adapter = viewPagerAdapter
    }

    private fun setUpBottomNav() {
        views.navBottom.menu.clear()
        views.navBottom.inflateMenu(R.menu.menu_bottom_figure)

        views.navBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> views.viewPager.setCurrentItem(0, true)
                R.id.nav_comment -> views.viewPager.setCurrentItem(1, true)
                R.id.nav_content -> views.viewPager.setCurrentItem(2, true)
                R.id.nav_custom -> {
                    HistoricalFigureCustomBottomFragment().show(childFragmentManager, HistoricalFigureCustomBottomFragment::class.java.name)
                }
            }
            true
        }
        views.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val currentPosition = if(position == 2 ) position + 1 else position
                views.navBottom.menu.getItem(currentPosition).isChecked = true
            }
        })
    }

    private fun listenStateViewModel() {
        viewModel.liveData.selectContent.observe(viewLifecycleOwner){
            views.viewPager.setCurrentItem(0, true)
        }

        viewModel.settingRepository.state.backgroundColor.observe(viewLifecycleOwner){
            val backgroundColor = viewModel.settingRepository.state.getBackgroundColor(requireContext())
            val isDarkMode = viewModel.settingRepository.state.isDarkMode

            views.navBottom.setBackgroundColor(backgroundColor)
            if(isDarkMode){
                views.navBottom.itemIconTintList = resources.getColorStateList(R.color.bottom_nav_color_dark, null)
                views.navBottom.itemTextColor = resources.getColorStateList(R.color.bottom_nav_color_dark, null)
            }else{
                views.navBottom.itemIconTintList = resources.getColorStateList(R.color.bottom_nav_color, null)
                views.navBottom.itemTextColor = resources.getColorStateList(R.color.bottom_nav_color, null)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearFigure()
    }
}