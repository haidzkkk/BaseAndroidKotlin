package com.app.motel.feature.territory

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.app.motel.data.model.Territory
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemTerritoryBinding

class TerritoryAdapter (
    private val listener: AppBaseAdapter.AppListener<Territory>
): AppBaseAdapter<Territory, ItemTerritoryBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemTerritoryBinding {
         return ItemTerritoryBinding.inflate(inflater, parent, false)
    }

    private var currentTerritoryId: String? = null
    private var currentTimeLineEntryId: String? = null
    @SuppressLint("NotifyDataSetChanged")
    fun setCurrentTerritoryId(territoryId: String?, timelineEntryId: String?) {
        currentTerritoryId = territoryId
        currentTimeLineEntryId = timelineEntryId
        notifyDataSetChanged()
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


        binding.lyTimeline.removeAllViews()
        item.timelineEntries?.forEach { value ->
            val isSelected = currentTerritoryId == item.id && currentTimeLineEntryId == value.id

            val itemView = LayoutInflater.from(binding.root.context)
                .inflate(R.layout.item_territory_timeline, binding.lyTimeline, false)

            val titleText = itemView.findViewById<TextView>(R.id.tv_title)
            titleText.text = value.title

            if(isSelected) titleText.setShadowLayer(10f, 0f, 0f, Color.YELLOW)
            else titleText.setShadowLayer(0f, 0f, 0f, Color.TRANSPARENT)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
            itemView.layoutParams = params

            binding.lyTimeline.addView(itemView)
        }

    }
}