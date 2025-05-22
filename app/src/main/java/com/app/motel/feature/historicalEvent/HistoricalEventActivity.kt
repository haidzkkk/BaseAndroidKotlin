package com.app.motel.feature.historicalEvent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.motel.data.model.Dynasty
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.auth.AuthActivity
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
import com.history.vietnam.ui.showDialogConfirm
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
        Log.e(" HistoricalEventActivity ", "item: $item")
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
            views.tvTitle.isVisible = isHomeFragment
            views.tvSubTitle.isVisible = isHomeFragment
            views.btnSave.isVisible = !isHomeFragment
        }
    }

    private fun setupToolBar(){
        views.tvTitle.text = "Thời kỳ quân chủ"
        views.tvSubTitle.text = ""
        views.btnBack.setOnClickListener {
            handleBackWithAnimation()
        }
        views.btnSave.setOnClickListener {
            viewModel.liveData.currentEvent.value?.apply {
                val isSaved = viewModel.userController.state.checkIsSaved(this.id, PageInfo.Type.HISTORICAL_EVENT) == true
                viewModel.userController.savePage(PageInfo.from(this), !isSaved)
            }
        }
    }

    private fun listenStateViewModel() {
        viewModel.settingRepository.state.backgroundColor.observe(this){
            val backgroundColor = viewModel.settingRepository.state.getBackgroundColor(this)

            views.lyToolbar.setBackgroundColor(backgroundColor)
            views.lyRoot.setBackgroundColor(backgroundColor)

            views.tvSubTitle.setTextColor(viewModel.settingRepository.state.getTextColor(this))
            views.btnBack.setColorFilter(viewModel.settingRepository.state.getTextColor(this))
            viewModel.userController.state.checkIsSaved(viewModel.liveData.currentEvent.value?.id, PageInfo.Type.HISTORICAL_EVENT)?.apply {
                views.btnSave.setColorFilter(if(this) ContextCompat.getColor(baseContext, R.color.gold) else viewModel.settingRepository.state.getTextColor(baseContext))
            }
        }

        viewModel.userController.state.loginUser.observe(this){
            if(it && !viewModel.userController.state.isLogin){
                showDialogConfirm(
                    title = "Opps!",
                    content = "Hãy đăng nhập để thực hiện chức năng này?",
                    buttonConfirm = "Đăng nhập",
                    buttonCancel = "Đóng",
                    confirm = {
                        startActivity(Intent(this, AuthActivity::class.java))
                    }
                )
            }
            viewModel.userController.state.loginUser.postValue(false)
        }

        viewModel.liveData.currentEvent.observe(this){
            views.btnSave.isVisible = it != null
            checkStatusSaveQuiz()
        }

        viewModel.userController.state.currentUser.observe(this){
            checkStatusSaveQuiz()
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
                    views.tvTitle.text = dynasty.first
                    views.tvSubTitle.text = dynasty.second
                }
                else ->{

                }
            }
        }
    }

    private fun checkStatusSaveQuiz(){
        viewModel.userController.state.checkIsSaved(viewModel.liveData.currentEvent.value?.id, PageInfo.Type.HISTORICAL_EVENT)?.apply {
            views.btnSave.setImageResource(if(this) R.drawable.icon_save_select else R.drawable.icon_save)
            views.btnSave.setColorFilter(if(this) ContextCompat.getColor(baseContext, R.color.gold) else viewModel.settingRepository.state.getTextColor(baseContext))
        }
    }

}