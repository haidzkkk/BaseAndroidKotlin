package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.Question
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.app.motel.ui.show
import com.app.motel.ultis.setWeight
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentQuizTestBinding
import com.history.vietnam.databinding.ItemQuestionBinding
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.navigateFragmentWithSlide
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class QuizTestFragment : AppBaseFragment<FragmentQuizTestBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizTestBinding {
        return FragmentQuizTestBinding.inflate(inflater, container, false)
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
        viewModel.initQuizTest()

        views.btnBack.setOnClickListener {
            popFragmentWithSlide()
        }
    }

    fun listenStateViewModel(){
        viewModel.liveData.timer.observe(viewLifecycleOwner){
            if(it == null) return@observe
            handleShowTimer(it)
        }
        viewModel.liveData.questions.observe(viewLifecycleOwner){
            if(it == null) return@observe
            viewModel.nextQuestion()
        }
        viewModel.liveData.currentQuestion.observe(viewLifecycleOwner){
            if(it == null) return@observe
            fillData()
        }
        viewModel.liveData.currentAnswerId.observe(viewLifecycleOwner){
            if(it == null) return@observe
            handleShowOption(it.question, it.answerQuestionId)

            viewModel.playCorrectSound(
                requireContext(),
                if(it.isCorrect)(R.raw.sound_correct)
                else R.raw.sound_incorrect
            )
        }
        viewModel.liveData.quizCompleted.observe(viewLifecycleOwner){
            if(it == null) return@observe
            when{
                it.isSuccess() -> {
                    handleCompletedQuizTest()
                }
                it.isError() -> {
                    popFragmentWithSlide()
                    requireContext().showToast(it.message ?: "Có lỗi xảy ra")
                }
            }
        }
    }

    private fun handleCompletedQuizTest(){
        viewModel.playCorrectSound(requireContext(),R.raw.sound_completed)
        navigateFragmentWithSlide(R.id.quizFinalFragment, replace = true)
    }

    @SuppressLint("DefaultLocale")
    private fun handleShowTimer(second: Int) {
        val minutes = second / 60
        val seconds = second % 60
        val formatted = String.format("%02d:%02d", minutes, seconds)
        views.tvTimer.text = formatted
    }

    @SuppressLint("SetTextI18n")
    private fun fillData(){
        val currentQuestion: Question? = viewModel.liveData.currentQuestion.value?.let {
            it.copy(answers = it.answers?.shuffled())
        }

        val totalAnswer = viewModel.liveData.answers.value?.size ?: 0
        val totalQuestion = (viewModel.liveData.questions.value?.size ?: 0) + 1 + totalAnswer
        val currentQuestionPosition = totalAnswer + 1

        val totalCorrectAnswer: Int = (viewModel.liveData.answers.value?.filter { it.isCorrect }?.size) ?: 0
        val totalIncorrectAnswer: Int = (viewModel.liveData.answers.value?.filter { it.isIncCorrect }?.size) ?: 0

        views.processCorrect.setWeight(totalCorrectAnswer.toFloat())
        views.processEmpty.setWeight((totalQuestion - currentQuestionPosition).toFloat())
        views.processIncorrect.setWeight(totalIncorrectAnswer.toFloat())

        views.tvQuestionCorrect.text = "$totalCorrectAnswer"
        views.tvQuestionIncorrect.text = "$totalIncorrectAnswer"
        views.tvCurrentQuestion.text = "Câu hỏi $currentQuestionPosition/$totalQuestion"
        views.tvQuestion.text = currentQuestion?.text
        views.img.show(
            currentQuestion?.image,
            onLoadFailed = {
                views.img.post {
                    views.img.show(viewModel.liveData.currentQuiz.value?.data?.image)
                }

            }
        )
        handleShowOption(currentQuestion)

        if(currentQuestion == null){
            requireContext().showToast("Không tìm thấy câu hỏi")
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun handleShowOption(currentQuestion: Question?, answerId: String? = null){
        views.lyQuestion.removeAllViews()

        currentQuestion?.answers?.forEachIndexed { _, value ->
            val selected = answerId != null
            val isSelect = answerId == value.id
            val isCorrect = currentQuestion.correctAnswerId == value.id

            val binding = ItemQuestionBinding.inflate(LayoutInflater.from(requireContext()), views.lyQuestion, false)

            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f).apply {
                binding.root.layoutParams = this
            }

            binding.tvQuestion.text = value.text

            if(isSelect){
                binding.lyMain.setBackgroundResource(R.drawable.background_border_2_select)
            }else{
                binding.lyMain.setBackgroundResource(R.drawable.background_border_2)
            }

            if(selected && isCorrect){
                binding.imgStatus.setBackgroundResource(R.drawable.icon_true)
            }else if(selected && isSelect){
                binding.imgStatus.setBackgroundResource(R.drawable.icon_false)
            }
            binding.imgStatus.isVisible = selected && isSelect

            if(answerId == null){
                binding.lyMain.setOnClickListener {
                    viewModel.submitAnswer(currentQuestion, value.id)
                }
            }

            views.lyQuestion.addView(binding.root)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearQuizTest()
    }
}