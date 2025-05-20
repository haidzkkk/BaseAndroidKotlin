package com.app.motel.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemSaveHorizontalBinding

class SavedHorizontalAdapter(
    private val listener: AppBaseAdapter.AppListener<PageInfo>
): AppBaseAdapter<PageInfo, ItemSaveHorizontalBinding>() {



    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemSaveHorizontalBinding {
         return ItemSaveHorizontalBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemSaveHorizontalBinding, item: PageInfo, position: Int) {
        binding.tvName.text = item.name
        binding.tvPeriod.text = item.year ?: "( - )"

        binding.root.setOnClickListener {
            listener.onClickItem(item, ItemAction.CLICK)
        }
    }
}