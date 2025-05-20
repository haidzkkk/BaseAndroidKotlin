package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.app.motel.data.model.AnswerQuestion
import com.app.motel.ui.show
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemQuestionBinding
import com.history.vietnam.databinding.ItemRadioButtonBinding
import com.history.vietnam.databinding.ItemRecheckQuizBinding
import com.history.vietnam.ultis.formatTopPosition

class RecheckQuestionAdapter (
    private val listener: AppBaseAdapter.AppListener<AnswerQuestion>
): AppBaseAdapter<AnswerQuestion, ItemRecheckQuizBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemRecheckQuizBinding {
         return ItemRecheckQuizBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ItemRecheckQuizBinding, item: AnswerQuestion, position: Int) {
        binding.tvPosition.visibility = View.VISIBLE
        binding.lyStatus.isVisible = true
        binding.lyPositionQuestion.isVisible = false
        binding.lyControl.isVisible = false

        binding.tvPosition.text = "${(position + 1).formatTopPosition()}."
        binding.tvQuestion.text = item.question.text
        binding.img.show(item.question.image, borderRadius = 20)

        binding.lyStatus.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, when{
            item.isNotAnswer -> R.color.grey
            item.isCorrect -> R.color.green
            item.isIncCorrect -> R.color.red
            else -> R.color.grey
        })

        binding.groupQuestion.removeAllViews()
        item.question.answers?.forEachIndexed { _, answer ->
            val bindingItem = ItemRadioButtonBinding.inflate(
                LayoutInflater.from(binding.root.context),
                binding.groupQuestion,
                false
            )
            bindingItem.radio.text = answer.text
            bindingItem.radio.isEnabled = false
            bindingItem.tvDesc.isVisible = false
            binding.groupQuestion.addView(bindingItem.root)
        }

        binding.root.setOnClickListener {
            listener.onClickItem(item, ItemAction.CLICK)
        }

        binding.groupQuestion.setOnClickListener {
            listener.onClickItem(item, ItemAction.CLICK)
        }
    }
}