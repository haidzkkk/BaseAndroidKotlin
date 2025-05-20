package com.app.motel.feature.quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.motel.data.model.Quiz
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.databinding.ActivityQuizBinding
import com.history.vietnam.ultis.finishActivityWithSlide
import com.history.vietnam.ultis.startActivityWithSlide
import javax.inject.Inject

class QuizActivity: AppBaseActivity<ActivityQuizBinding>() {

    companion object{
        const val QUIZ_KEY = "QUIZ_KEY"

        fun startActivity(activity: Activity, quiz: Quiz){
            activity.startActivityWithSlide(Intent(activity.baseContext, QuizActivity::class.java).apply {
                putExtra(QUIZ_KEY, Gson().toJson(quiz))
            })
        }
    }

    override fun getBinding(): ActivityQuizBinding {
         return ActivityQuizBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : QuizViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        init()
        listenStateViewModel()
    }

    private fun init() {
        val quiz: Quiz? = intent.getStringExtra(QUIZ_KEY)?.let { Gson().fromJson(it, Quiz::class.java) }
        viewModel.initQuiz(quiz)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackWithAnimation()
            }
        })
    }

    private fun listenStateViewModel() {

    }

    private fun handleBackWithAnimation() {
        val navController = findNavController(R.id.fragment_view)

        val popped = navController.popBackStack()
        if (!popped) {
            finishActivityWithSlide()
        }
    }
}