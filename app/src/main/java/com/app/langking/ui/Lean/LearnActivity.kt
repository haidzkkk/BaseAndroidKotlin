package com.app.langking.ui.Lean

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseActivity
import com.app.langking.data.model.Lesson
import com.app.langking.databinding.ActivityLearnBinding
import com.app.langking.databinding.ActivityMainBinding
import com.app.langking.ui.Home.HomeViewEvent
import com.app.langking.ui.Home.HomeViewModel
import com.app.langking.ui.Lean.viewmodel.LearnViewModel
import com.app.langking.ui.MainActivity
import com.app.langking.ui.auth.AuthActivity
import com.app.langking.ultis.startActivityWithTransition
import com.google.gson.Gson
import javax.inject.Inject

class LearnActivity : AppBaseActivity<ActivityLearnBinding>() {

    companion object{
        fun start(activity: Activity, lesson: Lesson){
            val intent = Intent(activity, LearnActivity::class.java)
            intent.putExtra("lesson", Gson().toJson(lesson))
            activity.startActivityWithTransition(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : LearnViewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[LearnViewModel::class.java]
    }

    override fun getBinding(): ActivityLearnBinding {
        return ActivityLearnBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TravleApplication).travleComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        handleData()

        viewModel.observeViewEvents {
//            when(it){
//                is HomeViewEvent.ReturnTestViewEvent ->  Log.e("TAG", "Test View event")
//                else -> {}
//            }
        }

        views.icHome.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        }
        views.icChat.setOnClickListener {
//            finishAffinity()
//            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun handleData(){
        val lessonJson: String? = intent.getStringExtra("lesson")
        val lesson: Lesson? = if(lessonJson != null) Gson().fromJson(lessonJson, Lesson::class.java) else null
        viewModel.initViewmodel(lesson)
    }

}