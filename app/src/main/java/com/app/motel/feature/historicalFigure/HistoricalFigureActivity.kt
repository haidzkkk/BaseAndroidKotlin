package com.app.motel.feature.historicalFigure

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.motel.data.model.Dynasty
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.historicalEvent.HistoricalEventActivity
import com.app.motel.feature.historicalEvent.HistoricalEventActivity.Companion
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewEvent
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.app.motel.feature.page.PageFragment
import com.app.motel.feature.page.viewmodel.PageViewEvent
import com.app.motel.feature.page.viewmodel.PageViewModel
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.databinding.ActivityHistoricalFigureBinding
import com.history.vietnam.ultis.finishActivityWithSlide
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class HistoricalFigureActivity : AppBaseActivity<ActivityHistoricalFigureBinding>() {

    companion object{
        const val ITEM_INFO_KEY = "ITEM_INFO_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HistoricalFigureViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HistoricalFigureViewModel::class.java)
    }

    override fun getBinding(): ActivityHistoricalFigureBinding {
        return ActivityHistoricalFigureBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        init()
        setupToolBar()
        listenStateViewModel()
        listenEventViewModel()
    }
    private fun init(){
        val item: PageInfo? = intent.getStringExtra(HistoricalEventActivity.ITEM_INFO_KEY)?.let { Gson().fromJson(it, PageInfo::class.java) }
        viewModel.setInfoSelect(item)

        if(viewModel.liveData.isSelectFigure && viewModel.liveData.infoSelect.value != null){
            val navController = findNavController(R.id.fragment_view)
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.nav_historical_figure)
            graph.setStartDestination(R.id.pageFragmentFigure)

            val bundle = PageFragment.getBundle(viewModel.liveData.infoSelect.value!!)
            navController.setGraph(graph, bundle)
        }


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackWithAnimation()
            }
        })
        findNavController(R.id.fragment_view).addOnDestinationChangedListener { controller, destination, arguments ->
            val isHomeFragment = destination.id == R.id.historicalFigureTimelineFragment
            supportActionBar?.setDisplayShowTitleEnabled(isHomeFragment)
        }
    }

    private fun setupToolBar(){
        setSupportActionBar(views.toolbar)
        (views.toolbar.context as AppCompatActivity).setSupportActionBar(views.toolbar)
        views.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        views.toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleStyle)
        views.toolbar.setSubtitleTextAppearance(this, R.style.ToolbarTitleStyle)
        views.toolbar.isTitleCentered = true
        views.toolbar.isSubtitleCentered = true
        supportActionBar?.title = "Thời kỳ quân chủ"
        supportActionBar?.subtitle = ""
        views.toolbar.setNavigationOnClickListener {
            handleBackWithAnimation()
        }
    }

    private fun handleBackWithAnimation() {
        val navController = findNavController(R.id.fragment_view)

        val popped = navController.popBackStack()
        if (!popped) {
            finishActivityWithSlide()
        }
    }

    private fun listenStateViewModel() {
        viewModel.settingRepository.state.backgroundColor.observe(this){
            val backgroundColor = viewModel.settingRepository.state.getBackgroundColor(this)

            views.toolbar.setBackgroundColor(backgroundColor)
            views.lyRoot.setBackgroundColor(backgroundColor)
            views.toolbar.setSubtitleTextColor(viewModel.settingRepository.state.getTextColor(this))
            views.toolbar.setNavigationIconTint(viewModel.settingRepository.state.getTextColor(this))
        }
    }

    private fun listenEventViewModel(){
        viewModel.observeViewEvents {
            when(it){
                is HistoricalFigureViewEvent.SetCurrentDynasty -> {
                    val dynasty = Dynasty.getDynasty(it.historyDynasty.fromDate)
                    supportActionBar?.title = dynasty.first
                    supportActionBar?.subtitle = dynasty.second
                    views.toolbar.invalidate()
                }
                else -> {

                }
            }
        }
    }
}