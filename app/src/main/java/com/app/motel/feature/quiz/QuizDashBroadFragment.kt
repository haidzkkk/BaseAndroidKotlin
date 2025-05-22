package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.auth.AuthActivity
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.app.motel.ui.show
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.databinding.FragmentQuizDashBroadBinding
import com.history.vietnam.databinding.FragmentQuizFinalBinding
import com.history.vietnam.ui.showDialogConfirm
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.AppConstants
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
        views.btnSave.setOnClickListener {
            viewModel.liveData.currentQuiz.value?.data?.apply {
                val isSaved = viewModel.userController.state.checkIsSaved(this.id, PageInfo.Type.QUIZ) == true
                viewModel.userController.savePage(PageInfo.from(this), !isSaved)
            }
        }
        views.btnShared.setOnClickListener {
            shareQuiz()
        }
    }

    private fun shareQuiz(){
        val currentQuiz = viewModel.liveData.currentQuiz.value?.data
        if(currentQuiz == null){
            requireActivity().showToast("Không tìm thấy quiz")
            return
        }

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "hãy luyện tập quiz này: ${AppConstants.DYNAMIC_LINK}?quizId=${currentQuiz.id}")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Chia sẻ qua"))

    }

    @SuppressLint("SetTextI18n")
    fun listenStateViewModel(){
        viewModel.liveData.currentQuiz.observe(viewLifecycleOwner){
            when{
                it.isSuccess() -> {
                    checkStatusSaveQuiz()

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

        viewModel.userController.state.loginUser.observe(viewLifecycleOwner){
            if(it && !viewModel.userController.state.isLogin){
                requireContext().showDialogConfirm(
                    title = "Opps!",
                    content = "Hãy đăng nhập để thực hiện chức năng này?",
                    buttonConfirm = "Đăng nhập",
                    buttonCancel = "Đóng",
                    confirm = {
                        requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                    }
                )
            }
            viewModel.userController.state.loginUser.postValue(false)
        }

        viewModel.userController.state.currentUser.observe(viewLifecycleOwner){
            checkStatusSaveQuiz()
        }
    }

    private fun checkStatusSaveQuiz(){
        viewModel.userController.state.checkIsSaved(viewModel.liveData.currentQuiz.value?.data?.id, PageInfo.Type.QUIZ)?.apply {
            views.btnSave.setImageResource(if(this) R.drawable.icon_save_select else R.drawable.icon_save)
            views.btnSave.setColorFilter(if(this) ContextCompat.getColor(requireContext(), R.color.gold) else ContextCompat.getColor(requireContext(), R.color.black))
        }
    }
}