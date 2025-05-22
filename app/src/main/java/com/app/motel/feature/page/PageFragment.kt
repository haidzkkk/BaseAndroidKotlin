package com.app.motel.feature.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.page.viewmodel.PageViewModel
import com.app.motel.ui.adapter.ViewPagerAdapter
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentPageBinding
import javax.inject.Inject

class PageFragment : AppBaseFragment<FragmentPageBinding>() {

    companion object{
        const val KEY_ITEM = "KEY_ITEM"

        fun getPageInfo(figure: HistoricalFigure, dynastyId: String? = null): Bundle {
            val pageInfo = PageInfo.from(figure, dynastyId)
            return Bundle().apply {
                putString(KEY_ITEM, Gson().toJson(pageInfo))
            }
        }

        fun getPageInfo(event: HistoricalEvent): Bundle {
            val pageInfo = PageInfo.from(event)
            return Bundle().apply {
                putString(KEY_ITEM, Gson().toJson(pageInfo))
            }
        }

        fun getBundle(pageInfo: PageInfo): Bundle{
            return Bundle().apply {
                putString(KEY_ITEM, Gson().toJson(pageInfo))
            }
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPageBinding {
        return FragmentPageBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : PageViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(PageViewModel::class.java)
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
        val pageInfo: PageInfo? = arguments?.getString(KEY_ITEM)?.let {
            Gson().fromJson(it, PageInfo::class.java)
        }
        viewModel.initPage(pageInfo)

        val fragments = arrayListOf(
            PageHomeFragment(),
            CommentFragment(),
            IndexFragment()
        )
        viewPagerAdapter = ViewPagerAdapter(fragments, childFragmentManager, lifecycle)
        views.viewPager.adapter = viewPagerAdapter
    }

    private fun setUpBottomNav() {
        views.navBottom.menu.clear()
        views.navBottom.inflateMenu(R.menu.menu_page)

        views.navBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> views.viewPager.setCurrentItem(0, true)
                R.id.nav_comment -> views.viewPager.setCurrentItem(1, true)
                R.id.nav_content -> views.viewPager.setCurrentItem(2, true)
                R.id.nav_custom -> {
                    SettingBottomFragment().show(childFragmentManager, SettingBottomFragment::class.java.name)
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
        viewModel.liveData.pageInfo.observe(viewLifecycleOwner){
            if(it.action == PageInfo.Action.LIKE || it.action == PageInfo.Action.COMMENT){
                views.navBottom.menu.getItem(1).isChecked = true
                views.viewPager.setCurrentItem(1, true)
            }
        }
        viewModel.liveData.selectContent.observe(viewLifecycleOwner){
            views.viewPager.setCurrentItem(0, true)
        }

        viewModel.settingController.state.backgroundColor.observe(viewLifecycleOwner){
            val backgroundColor = viewModel.settingController.state.getBackgroundColor(requireContext())
            val isDarkMode = viewModel.settingController.state.isDarkMode

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