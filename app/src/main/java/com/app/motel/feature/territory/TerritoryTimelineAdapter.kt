package com.app.motel.feature.territory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.motel.data.model.Section
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemTerritoryTimelineBinding

class TerritoryTimelineAdapter: AppBaseAdapter<Section, ItemTerritoryTimelineBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemTerritoryTimelineBinding {
         return ItemTerritoryTimelineBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemTerritoryTimelineBinding>,
        position: Int
    ) {
        val totalItems = itemCount
        if(totalItems < 12){
            holder.itemView.post {
                val parentHeight = (holder.itemView.parent as? RecyclerView)?.height ?: return@post
                val itemHeight = parentHeight / totalItems

                holder.itemView.layoutParams.height = itemHeight + 1
                holder.itemView.requestLayout()
            }
        }

        super.onBindViewHolder(holder, position)
    }

    override fun bind(binding: ItemTerritoryTimelineBinding, item: Section, position: Int) {
        binding.tvTitle.text = item.title
    }
}