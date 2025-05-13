package com.app.motel.feature.territory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.app.motel.data.model.Territory
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemTerritoryBinding

class TerritoryAdapter (
    private val listener: AppBaseAdapter.AppListener<Territory>
): AppBaseAdapter<Territory, ItemTerritoryBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemTerritoryBinding {
         return ItemTerritoryBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("DiscouragedApi", "NotifyDataSetChanged")
    override fun bind(binding: ItemTerritoryBinding, item: Territory, position: Int) {
        binding.tvTitle.text = item.title
        binding.tvPeriod.text = item.period
        binding.tvContent.text = item.description

        binding.tvPeriod.isVisible = !item.period.isNullOrEmpty()

        binding.root.context.apply {
            val resId = this.resources.getIdentifier(item.image, "drawable", packageName)
            binding.imgImage.setImageResource(resId)
        }

        val adapter = TerritoryTimelineAdapter()
        binding.rcv.adapter = adapter
        adapter.updateData(item.timelineEntries)

    }
}