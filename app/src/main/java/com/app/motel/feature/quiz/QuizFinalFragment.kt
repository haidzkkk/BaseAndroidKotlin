package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.databinding.FragmentQuizFinalBinding
import com.history.vietnam.databinding.FragmentQuizTestBinding
import com.history.vietnam.ultis.finishActivityWithSlide
import com.history.vietnam.ultis.navigateFragmentWithSlide
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class QuizFinalFragment : AppBaseFragment<FragmentQuizFinalBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizFinalBinding {
        return FragmentQuizFinalBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : QuizViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(QuizViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    fun init(){
        views.btnBack.setOnClickListener {
            popFragmentWithSlide()
        }
        views.btnReplay.setOnClickListener {
            navigateFragmentWithSlide(R.id.quizDashBroadFragment, clearBackStack = true)
        }
        views.btnCheckQuestion.setOnClickListener {
            navigateFragmentWithSlide(R.id.quizRecheckFragment)
        }
        views.btnHome.setOnClickListener {
            requireActivity().apply {
                finishActivityWithSlide()
            }
        }
        views.btnRank.setOnClickListener {
            navigateFragmentWithSlide(R.id.quizRankingFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    fun listenStateViewModel(){
        viewModel.liveData.quizCompleted.observe(viewLifecycleOwner){
            views.tvScore.text = "${it.data?.score ?: 0} pt"
            views.tvProcess.text = "${it.data?.processCompleted ?: 0}%"
            views.tvTotalQuestion.text = "${it.data?.totalQuestions ?: 0}"
            views.tvCorrect.text = "${it.data?.correctAnswers ?: 0}"
            views.tvIncorrect.text = "${it.data?.incorrectAnswers ?: 0}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.liveData.quizCompleted.postValue(Resource.Initialize())
    }
}