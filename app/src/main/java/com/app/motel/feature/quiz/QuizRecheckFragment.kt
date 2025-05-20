package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.AnswerQuestion
import com.app.motel.data.model.Question
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.app.motel.ui.show
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseDialog
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.DialogLoadingBinding
import com.history.vietnam.databinding.FragmentQuizRecheckBinding
import com.history.vietnam.databinding.ItemRadioButtonBinding
import com.history.vietnam.databinding.ItemRecheckQuizBinding
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.formatTopPosition
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class QuizRecheckFragment : AppBaseFragment<FragmentQuizRecheckBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizRecheckBinding {
        return FragmentQuizRecheckBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : QuizViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(QuizViewModel::class.java)
    }

    val adapter = RecheckQuestionAdapter(object : AppBaseAdapter.AppListener<AnswerQuestion>(){
        override fun onClickItem(item: AnswerQuestion, action: AppBaseAdapter.ItemAction) {
            showDialogAnswer(item)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    fun init(){
        views.rcv.adapter = adapter

        views.btnBack.setOnClickListener {
            popFragmentWithSlide()
        }
    }

    fun listenStateViewModel(){
        viewModel.liveData.quizCompleted.observe(viewLifecycleOwner){
            val questionAnswered = it.data?.getListQuestionAnswered
            adapter.updateData(questionAnswered)
        }
    }

    fun showDialogAnswer(item: AnswerQuestion?){

        val dialog = AppBaseDialog.Builder(requireContext(), ItemRecheckQuizBinding.inflate(layoutInflater))
            .isBorderRadius(true)
            .build()


        dialogHandleFillData(dialog, item)

        dialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun dialogHandleFillData(
        dialog: AppBaseDialog<ItemRecheckQuizBinding>,
        currentItem: AnswerQuestion?,
    ){
        val listData = viewModel.liveData.quizCompleted.value?.data?.getListQuestionAnswered
        val position = listData?.indexOfFirst { currentItem?.question?.id == it.question.id } ?: -1
        if(position == -1 || currentItem == null){
            dialog.dismiss()
            dialog.binding.root.context.showToast("Không tìm thấy câu hỏi này!")
        }


        val binding = dialog.binding

        binding.tvPosition.visibility = View.INVISIBLE
        binding.lyStatus.isVisible = false
        binding.lyPositionQuestion.isVisible = true
        binding.lyControl.isVisible = true

        binding.tvPositionQuestion.text = "Câu ${(position + 1).formatTopPosition()}"
        binding.tvQuestion.text = currentItem?.question?.text
        binding.img.show(currentItem?.question?.image, borderRadius = 20)

        when{
            currentItem?.isCorrect == true -> R.drawable.icon_true
            currentItem?.isIncCorrect == true -> R.drawable.icon_false
            else -> null
        }.apply {
            binding.iconStatus.isVisible = this != null
            binding.tvPositionQuestionDesc.isVisible = this == null
            if(this != null){
                binding.iconStatus.isVisible
                binding.iconStatus.setImageResource(this)
            }
        }

        binding.groupQuestion.removeAllViews()
        currentItem?.question?.answers?.forEachIndexed { _, answer ->
            val bindingItem = ItemRadioButtonBinding.inflate(
                LayoutInflater.from(binding.root.context),
                binding.groupQuestion,
                false
            )
            bindingItem.radio.text = answer.text
            bindingItem.radio.isEnabled = false
            bindingItem.tvDesc.isVisible = false

            val isItemSelect = answer.id == currentItem.answerQuestionId
            val isItemCorrect = answer.id == currentItem.question.correctAnswerId

            bindingItem.radio.isChecked = isItemSelect || isItemCorrect
            bindingItem.tvDesc.isVisible = isItemSelect
            if(isItemSelect){
                bindingItem.tvDesc.text = "Câu bạn chọn"
                (if(isItemCorrect) R.color.green else R.color.red).apply {
                    bindingItem.tvDesc.setTextColor(ContextCompat.getColor(binding.root.context, this))
                    bindingItem.radio.setButtonTintList(ContextCompat.getColorStateList(binding.root.context,this))
                }
            }
            if(isItemCorrect){
                bindingItem.radio.setButtonTintList(ContextCompat.getColorStateList(binding.root.context,R.color.green))
            }

            binding.groupQuestion.addView(bindingItem.root)
        }


        binding.btnBack.setOnClickListener {
            if(position > 0){
                dialogHandleFillData(dialog, listData?.get(position - 1))
            }else{
                dialog.dismiss()
            }
        }
        binding.btnContinue.setOnClickListener {
            if(position < (listData?.size?.minus(1) ?: 0)){
                dialogHandleFillData(dialog, listData?.get(position + 1))
            }else{
                dialog.dismiss()
            }
        }
    }
}