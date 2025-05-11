package com.app.motel.feature.historicalFigure.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.feature.setting.SettingController
import com.app.motel.ui.setCollapseAnimation
import com.app.motel.ui.setExpandAnimation
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemDynastyBinding

class DynastyAdapter constructor(
    private val settingController: SettingController,
    private val listenerDynasty: AppBaseAdapter.AppListener<HistoryDynasty>,
    private val listenerFigure: AppBaseAdapter.AppListener<HistoricalFigure>,
): AppBaseAdapter<HistoryDynasty, ItemDynastyBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemDynastyBinding {
         return ItemDynastyBinding.inflate(inflater, parent, false)
    }

    override fun updateData(newItems: List<HistoryDynasty>?) {
        super.updateData(newItems)
        if(items.size <= selectedPosition){
            selectedPosition = 0
        }
        if(items.isEmpty()) return
        listenerDynasty.onClickItem(items[selectedPosition])
    }

    private val adapter = FigureAdapter(settingController, listenerFigure)
    private var selectedPosition = 0

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun bind(binding: ItemDynastyBinding, item: HistoryDynasty, position: Int) {
        binding.rcv.setBackgroundColor(settingController.state.getBackgroundColor(binding.root.context))
        binding.tvName.typeface = settingController.state.getTextFont(binding.root.context)

        binding.tvName.text = "${item.name} (${item.fromDate} - ${item.toDate})"

        val isSelected = position == selectedPosition
        binding.rcv.isVisible = isSelected
        binding.imgSelect.setImageResource(
            if (isSelected) R.drawable.baseline_keyboard_arrow_down_24
            else R.drawable.baseline_keyboard_arrow_right_24
        )

        if (isSelected) {
            binding.rcv.adapter = adapter
            adapter.updateData(item.figures)
        }

        binding.lySelect.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = if (selectedPosition == position) -1 else position

            // expand collapse item with animation
            if (selectedPosition == position) {
                binding.rcv.setCollapseAnimation()
            } else {
                binding.rcv.setExpandAnimation()
            }

            // update item
            if (previous >= 0) notifyItemChanged(previous)
            notifyItemChanged(position)

            // send event item select to activity
            if (selectedPosition >= 0) {
                listenerDynasty.onClickItem(items[selectedPosition])
            }
        }
    }
}