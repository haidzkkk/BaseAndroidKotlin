package com.app.motel.feature.historicalEvent

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.feature.setting.SettingController
import com.app.motel.ui.setCollapseAnimation
import com.app.motel.ui.setExpandAnimation
import com.app.motel.ui.show
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemDynastyBinding
import com.history.vietnam.databinding.ItemEventBinding

class EventAdapter constructor(
    private val settingController: SettingController,
    private val listener: AppBaseAdapter.AppListener<HistoricalEvent>,
): AppBaseAdapter<HistoricalEvent, ItemEventBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemEventBinding {
         return ItemEventBinding.inflate(inflater, parent, false)
    }

    override fun updateData(newItems: List<HistoricalEvent>?) {
        super.updateData(newItems)
        if(items.size <= selectedPosition){
            selectedPosition = 0
        }
    }

    private var selectedPosition = 0

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun bind(binding: ItemEventBinding, item: HistoricalEvent, position: Int) {
//        settingController

        val isSelected = position == selectedPosition

        binding.tvTitle.text = item.birthYear
        binding.tvContent.text = item.name

        binding.cvImage.isVisible = isSelected
        binding.lySelect.isVisible = item.getLevel == HistoricalEvent.Level.IMPORTANT

        if(isSelected){
            binding.image.show(item.image,
                scaleType = CenterCrop(),
                onLoadFailed = {
                    listener.onClickItem(items[selectedPosition])
                    binding.cvImage.isVisible = false
                })
        }

        when (item.getLevel) {
            HistoricalEvent.Level.IMPORTANT, HistoricalEvent.Level.SUB_IMPORTANT -> {

                binding.tvTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.primary))
                binding.tvContent.setTextColor(ContextCompat.getColor(binding.root.context, R.color.primary))
                binding.tvTitle.setTypeface(null, Typeface.NORMAL)
                binding.tvContent.setTypeface(null, Typeface.BOLD)

            }
            HistoricalEvent.Level.NORMAL -> {
                binding.tvTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.primary))
                binding.tvContent.setTextColor(ContextCompat.getColor(binding.root.context, R.color.textColor1))
                binding.tvTitle.setTypeface(null, Typeface.NORMAL)
                binding.tvContent.setTypeface(null, Typeface.NORMAL)

            }
        }


        binding.root.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = position

            if (previous != position) {
                binding.cvImage.setCollapseAnimation()
            } else {
                binding.cvImage.setExpandAnimation()
            }

            if(previous == position){
                listener.onClickItem(items[previous])
            }

            // update item
            if (previous >= 0) notifyItemChanged(previous)
            notifyItemChanged(position)

        }
    }
}