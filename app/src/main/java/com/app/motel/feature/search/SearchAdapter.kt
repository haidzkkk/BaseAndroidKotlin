package com.app.motel.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemSearchBinding

class SearchAdapter (
    private val listener: AppBaseAdapter.AppListener<PageInfo>
): AppBaseAdapter<PageInfo, ItemSearchBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup,
        viewType: Int): ItemSearchBinding {
         return ItemSearchBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemSearchBinding, item: PageInfo, position: Int) {
        binding.tvTitle.text = item.name
        binding.tvContent.text = item.year

        binding.btnTo.isVisible = !item.firebasePath.isNullOrEmpty()
        binding.btnPost.isVisible = !item.wikiPageId.isNullOrEmpty()

        binding.root.setOnClickListener {
            if(!item.wikiPageId.isNullOrEmpty()){
                listener.onClickItem(item.copy().apply { action = PageInfo.Action.DETAIL })
            }else{
                listener.onClickItem(item.copy().apply { action = PageInfo.Action.TIME_LINE })
            }
        }
        binding.btnTo.setOnClickListener {
            listener.onClickItem(item.copy().apply { action = PageInfo.Action.TIME_LINE })
        }
        binding.btnPost.setOnClickListener {
            listener.onClickItem(item.copy().apply { action = PageInfo.Action.DETAIL })
        }
    }
}