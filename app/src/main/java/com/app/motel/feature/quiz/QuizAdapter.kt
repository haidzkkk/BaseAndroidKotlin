package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.motel.data.model.Quiz
import com.app.motel.ui.show
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemQuizBinding

class QuizAdapter (
    private val listener: AppBaseAdapter.AppListener<Quiz>
): AppBaseAdapter<Quiz, ItemQuizBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup,
        viewType: Int): ItemQuizBinding {
         return ItemQuizBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ItemQuizBinding, item: Quiz, position: Int) {
        binding.tvName.text = item.title
        binding.tvPeriod.text = item.period
        binding.tvTotalQuestion.text = "${item.questionCount ?: 0} câu hỏi - ${item.durationMinutes ?: 0} phút"
        binding.tvPlayCount.text = "${item.playCount ?: 0} lượt chơi"
        binding.img.show(item.image, borderRadius = 10)

        binding.root.setOnClickListener{
            listener.onClickItem(item)
        }
    }
}