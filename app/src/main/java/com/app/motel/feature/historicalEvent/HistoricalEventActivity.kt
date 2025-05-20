package com.app.motel.feature.historicalEvent

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.motel.data.model.Dynasty
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.historicalEvent.viewmodel.HistoricalEventViewEvent
import com.app.motel.feature.historicalEvent.viewmodel.HistoricalEventViewModel
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.app.motel.feature.page.PageFragment
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.databinding.ActivityHistoricalEventBinding
import com.history.vietnam.databinding.ActivityHistoricalFigureBinding
import com.history.vietnam.ultis.finishActivityWithSlide
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class HistoricalEventActivity : AppBaseActivity<ActivityHistoricalEventBinding>() {

    companion object{
        const val ITEM_INFO_KEY = "ITEM_INFO_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HistoricalEventViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HistoricalEventViewModel::class.java)
    }

    override fun getBinding(): ActivityHistoricalEventBinding {
        return ActivityHistoricalEventBinding.inflate(layoutInflater)
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
        val item: PageInfo? = intent.getStringExtra(ITEM_INFO_KEY)?.let { Gson().fromJson(it, PageInfo::class.java) }
        viewModel.setInfoSelect(item)

        if(viewModel.liveData.isSelectInfoDetail && viewModel.liveData.infoSelect.value != null){
            val navController = findNavController(R.id.fragment_view)
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.nav_historical_event)
            graph.setStartDestination(R.id.pageFragmentEvent)

            val bundle = PageFragment.getBundle(viewModel.liveData.infoSelect.value!!)
            navController.setGraph(graph, bundle)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackWithAnimation()
            }
        })
        findNavController(R.id.fragment_view).addOnDestinationChangedListener { controller, destination, arguments ->
            val isHomeFragment = destination.id == R.id.historicalEventTimeLineFragment
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

    private fun listenStateViewModel() {
        viewModel.settingRepository.state.backgroundColor.observe(this){
            val backgroundColor = viewModel.settingRepository.state.getBackgroundColor(this)

            views.toolbar.setBackgroundColor(backgroundColor)
            views.lyRoot.setBackgroundColor(backgroundColor)
            views.toolbar.setSubtitleTextColor(viewModel.settingRepository.state.getTextColor(this))
            views.toolbar.setNavigationIconTint(viewModel.settingRepository.state.getTextColor(this))
        }
    }

    private fun handleBackWithAnimation() {
        val navController = findNavController(R.id.fragment_view)

        val popped = navController.popBackStack()
        if (!popped) {
            finishActivityWithSlide()
        }
    }

    private fun listenEventViewModel() {
        viewModel.observeViewEvents {
            when(it){
                is HistoricalEventViewEvent.SetCurrentEvent -> {
                    val dynasty = Dynasty.getDynasty(it.historicalEvent.birthYear)
                    supportActionBar?.title = dynasty.first
                    supportActionBar?.subtitle = dynasty.second
                    views.toolbar.invalidate()
                }
                else ->{

                }
            }
        }
    }

}