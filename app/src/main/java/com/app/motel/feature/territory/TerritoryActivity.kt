package com.app.motel.feature.territory

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.app.motel.data.model.Dynasty
import com.app.motel.feature.territory.viewmodel.TerritoryViewEvent
import com.app.motel.feature.territory.viewmodel.TerritoryViewModel
import com.app.motel.ui.adapter.ViewPagerAdapter
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.databinding.ActivityTerritoryBinding
import javax.inject.Inject

class TerritoryActivity : AppBaseActivity<ActivityTerritoryBinding>() {

    companion object{
        const val ITEM_FIGURE_KEY = "ITEM_FIGURE_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TerritoryViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TerritoryViewModel::class.java)
    }

    override fun getBinding(): ActivityTerritoryBinding {
        return ActivityTerritoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        init()
        setupToolBar()
        setUpBottomNav()
        listenStateViewModel()
        listenEventViewModel()
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private fun init(){
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackWithAnimation()
            }
        })

        val fragments = arrayListOf(
            TerritoryHomeFragment(),
            TerritoryCommentFragment(),
            TerritoryContentFragment(),
        )
        viewPagerAdapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        views.viewPager.adapter = viewPagerAdapter
    }

    private fun setupToolBar(){
        setSupportActionBar(views.toolbar)
        (views.toolbar.context as AppCompatActivity).setSupportActionBar(views.toolbar)
        views.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        views.toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleStyle2)
        views.toolbar.setSubtitleTextAppearance(this, R.style.ToolbarTitleStyle2)
        views.toolbar.isTitleCentered = true
        views.toolbar.isSubtitleCentered = true
        supportActionBar?.title = ""
        supportActionBar?.subtitle = ""
        views.toolbar.setNavigationOnClickListener {
            handleBackWithAnimation()
        }
    }

    private fun setUpBottomNav() {
        views.navBottom.menu.clear()
        views.navBottom.inflateMenu(R.menu.menu_territory)

        views.navBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> views.viewPager.setCurrentItem(0, true)
                R.id.nav_comment -> views.viewPager.setCurrentItem(1, true)
                R.id.nav_content -> views.viewPager.setCurrentItem(2, true)
            }
            true
        }
        views.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                views.navBottom.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun handleBackWithAnimation() {
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun listenStateViewModel() {
        viewModel.liveData.selectContent.observe(this){
            views.viewPager.setCurrentItem(0, true)


        }
    }

    private fun listenEventViewModel() {
        viewModel.observeViewEvents {
            when(it){
                is TerritoryViewEvent.SetCurrentTerritory -> {
                    supportActionBar?.title = it.territory.title ?: ""
                    supportActionBar?.subtitle = it.territory.period ?: ""
                    views.toolbar.invalidate()
                }
                else ->{

                }
            }
        }
    }

}