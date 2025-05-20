package com.app.motel.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemSaveHorizontalBinding
import com.history.vietnam.databinding.ItemSaveVerticalBinding

class SavedVerticalAdapter(
    private val listener: AppBaseAdapter.AppListener<PageInfo>
): AppBaseAdapter<PageInfo, ItemSaveVerticalBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemSaveVerticalBinding {
         return ItemSaveVerticalBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemSaveVerticalBinding, item: PageInfo, position: Int) {
        binding.tvName.text = item.name
        binding.tvPeriod.text = item.year ?: "( - )"

        binding.root.setOnClickListener {
            listener.onClickItem(item, ItemAction.CLICK)
        }
        binding.root.setOnLongClickListener {
            listener.onClickItem(item, ItemAction.LONG_CLICK)
            true
        }
    }
}