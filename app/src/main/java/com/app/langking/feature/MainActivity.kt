package com.app.langking.feature

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseActivity
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.databinding.ActivityMainBinding
import com.app.langking.feature.Home.HomeViewAction
import com.app.langking.feature.Home.HomeViewEvent
import com.app.langking.feature.Home.HomeViewModel
import com.app.langking.feature.Learn.LearnActivity
import com.app.langking.ultis.Status
import com.app.langking.ultis.observeOnce
import javax.inject.Inject

class MainActivity : AppBaseActivity<ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel : HomeViewModel by lazy{
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TravleApplication).travleComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        viewModel.observeViewEvents {
            when(it){
                is HomeViewEvent.ReturnTestViewEvent ->  Log.e("TAG", "Test View event")
                else -> {}
            }
        }
        requestNotificationPermission()
        setUpBottomNav()
        handleStateViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handle(HomeViewAction.updateViewAction)
    }

    private fun setUpBottomNav() {
        val navController = this.findNavController(R.id.fragment_view)
        views.navBottom.setupWithNavController(navController)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


    private fun handleStateViewModel(){
        val lessonId = intent?.extras?.getInt("lesson_id")
        Log.e("TAG", "Intent: lessonId: $lessonId")
        viewModel.liveData.apply {
            if(lessonId != null){
                categories.observeOnce(this@MainActivity) {
                    when(it.status){
                        Status.SUCCESS ->{
                            var lessonSearch: Lesson? = null
                            it.data?.forEach { category: Category ->
                                category.lessons?.forEach{ lesson: Lesson ->
                                    if(lesson.id == lessonId){
                                        lessonSearch = lesson
                                    }
                                }
                            }

                            if(lessonSearch != null){
                                LearnActivity.start(this@MainActivity, lessonSearch!!)
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1000
    }
}