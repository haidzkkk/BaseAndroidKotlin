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

        fun getPageInfo(figure: HistoricalFigure): Bundle {
            val pageInfo = PageInfo(
                name = figure.name,
                wikiPageId = figure.wikiPageId,
                firebaseId = figure.id?.toString(),
                firebasePath = "sadsadsad/đá/${figure.id}",
                info = mapOf(
                    "Sinh" to (figure.birthYear ?: ""),
                    "Mất" to (figure.deathDate ?: ""),
                    "Vợ" to (figure.spouse ?: ""),
                    "Tước hiệu" to (figure.title ?: ""),
                    "Triều đại" to (figure.dynasty ?: ""),
                ),
            )

            return Bundle().apply {
                putString(KEY_ITEM, Gson().toJson(pageInfo))
            }
        }

        fun getPageInfo(event: HistoricalEvent): Bundle {
            val pageInfo = PageInfo(
                name = event.name,
                wikiPageId = event.wikiPageId,
                firebaseId = event.id?.toString(),
                firebasePath = "sadsadsad/đá/${event.id}",
                info = mapOf(
                    "Thời gian" to (event.birthYear ?: ""),
                    "Triều đại" to (event.dynasty ?: ""),
                    "Triều đại" to (event.dynasty ?: ""),
                ),
            )

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
        viewModel.initFigure(pageInfo)

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