package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.app.motel.ui.show
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.databinding.FragmentQuizDashBroadBinding
import com.history.vietnam.databinding.FragmentQuizFinalBinding
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.navigateFragmentWithSlide
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class QuizDashBroadFragment : AppBaseFragment<FragmentQuizDashBroadBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizDashBroadBinding {
        return FragmentQuizDashBroadBinding.inflate(inflater, container, false)
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
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        views.btnStart.setOnClickListener {
            if(viewModel.liveData.currentQuiz.value?.hasData() != true){
                requireActivity().showToast("Không tìm thấy quiz")
                return@setOnClickListener
            }
            navigateFragmentWithSlide(R.id.quizTestFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    fun listenStateViewModel(){
        viewModel.liveData.currentQuiz.observe(viewLifecycleOwner){
            when{
                it.isSuccess() -> {
                    views.img.show(it.data?.image, borderRadius = 10)
                    views.tvTvName.text = it.data?.title
                    views.tvPeriod.text = it.data?.period
                    views.tvTotalQuestion.text = "Số câu hỏi: ${it.data?.questionCount}"
                    views.tvTime.text = "Thời gian: ${it.data?.durationMinutes}"
                }
                it.isError() -> {
                    requireActivity().showToast(it.message ?: "Có lỗi xảy ra")
                }
                else -> {}
            }
        }
    }
}