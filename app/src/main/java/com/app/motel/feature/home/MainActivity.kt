package com.app.motel.feature.home

import android.Manifest
import android.R.string
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.motel.data.model.AppNotification
import com.app.motel.data.service.FCMService
import com.app.motel.feature.notification.viewmodel.NotificationViewModel
import com.app.motel.feature.profile.UserController
import com.app.motel.feature.quiz.QuizActivity
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.google.firebase.messaging.Constants.MessagePayloadKeys
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.databinding.ActivityMainBinding
import com.history.vietnam.feature.Home.HomeViewEvent
import com.history.vietnam.feature.Home.HomeViewModel
import javax.inject.Inject


class MainActivity : AppBaseActivity<ActivityMainBinding>() {

    companion object{
        const val notificationPayload = "notificationPayload"
    }

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : HomeViewModel by lazy{
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    private val notificationViewModel : NotificationViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(NotificationViewModel::class.java)
    }

    @Inject
    lateinit var userController: UserController

    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onResume getCurrentUser: ")
        userController.getCurrentUser()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        init()
        listenStateViewModel()
        handleListenViewEvent()
    }

    private fun init() {
        hasAskedNotificationPermission = false

        views.root.post {
            setUpBottomNav()
        }
        extractAndHandleNotification()
        extractAndHandleDynamicLink()
    }

    private fun handleListenViewEvent() {
        mViewModel.observeViewEvents {
            when(it){
                is HomeViewEvent.ReturnTestViewEvent ->  Log.e("TAG", "Test View event")
                else -> {}
            }
        }
    }

    private fun extractAndHandleDynamicLink() {
        val intentData = intent?.data
        if (intentData != null) {
            val id = intentData.getQueryParameter("quizId")
            id?.apply {
                QuizActivity.startActivity(this@MainActivity, this)
            }
        }
    }

    private fun extractAndHandleNotification() {
        val notification: AppNotification? = if(intent.getStringExtra(notificationPayload) != null){
            Gson().fromJson(intent.getStringExtra(notificationPayload), AppNotification::class.java)
        } else intent.extras?.let{
            val payload =  MessagePayloadKeys.extractDeveloperDefinedPayload(it)
            FCMService.mapToAppNotification(payload)
        }
        notification?.apply { notificationViewModel.handleDataRedirect(this) }
    }

    private fun setUpBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_view) as NavHostFragment
        val navController = navHostFragment.navController
        views.navBottom.setupWithNavController(navController)

        views.navBottom.menu.clear()
        views.navBottom.inflateMenu(R.menu.menu_bottom)
        this.findNavController(R.id.fragment_view).currentDestination?.id?.apply {
            views.navBottom.selectedItemId = this
        }
        this.findNavController(R.id.fragment_view).addOnDestinationChangedListener { controller, destination, arguments ->
            destination.id.let { currentLayoutId ->
                views.navBottom.isVisible = currentLayoutId == R.id.nav_home || currentLayoutId == R.id.nav_profile || currentLayoutId == R.id.nav_quiz
            }
        }
    }

    private fun listenStateViewModel() {
        userController.state.currentUser.observe(this){
            notificationViewModel.init()
            if(it.data != null){
                handleRequestPermission()
            }
        }
        notificationViewModel.liveData.redirectPageInfo.observe(this){
            it?.startActivity(this)
            notificationViewModel.liveData.redirectPageInfo.postValue(null)
        }
    }

    private var hasAskedNotificationPermission = false
    private fun handleRequestPermission(){
        if (!hasAskedNotificationPermission && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            hasAskedNotificationPermission = true
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    999
                )
            }
        }
    }
}