package com.app.langking.feature.Learn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseActivity
import com.app.langking.data.model.Lesson
import com.app.langking.databinding.ActivityLearnBinding
import com.app.langking.feature.Learn.viewmodel.LearnViewModel
import com.app.langking.feature.MainActivity
import com.app.langking.feature.inbox.InboxFragment
import com.app.langking.ultis.navigateFragmentWithSlide
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
            val bundle = Bundle().apply {
                putString(InboxFragment.WORDS, Gson().toJson(viewModel.liveData.currentLesson.value?.words))
            }
            navigateFragmentWithSlide(R.id.fragment_view, R.id.inboxFragment, bundle)
        }
    }

    private fun handleData(){
        val lessonJson: String? = intent.getStringExtra("lesson")
        val lesson: Lesson? = if(lessonJson != null) Gson().fromJson(lessonJson, Lesson::class.java) else null
        viewModel.initViewmodel(lesson)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}