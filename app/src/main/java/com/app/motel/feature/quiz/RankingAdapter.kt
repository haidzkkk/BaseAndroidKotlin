package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.motel.data.model.Ranking
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemRankingBinding
import com.history.vietnam.ultis.formatTopPosition

class RankingAdapter(
    private val listener: AppBaseAdapter.AppListener<Ranking>
): AppBaseAdapter<Ranking, ItemRankingBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup,
        viewType: Int): ItemRankingBinding {
         return ItemRankingBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ItemRankingBinding, item: Ranking, position: Int) {
        binding.tvPosition.text = (position + 4).formatTopPosition()
        binding.tvName.text = item.user?.name ?: "Ẩn danh ${(position + 4).formatTopPosition()}"
        binding.tvScore.text = "${item.score ?: 0}pt"
    }
}