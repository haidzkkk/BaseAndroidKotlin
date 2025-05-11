package com.app.motel.feature.historicalFigure.adapter

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.feature.setting.SettingController
import com.app.motel.ui.show
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemFigureBinding

class FigureAdapter constructor(
    private val settingController: SettingController,
    private val listener: AppBaseAdapter.AppListener<HistoricalFigure>
): AppBaseAdapter<HistoricalFigure, ItemFigureBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemFigureBinding {
         return ItemFigureBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ItemFigureBinding, item: HistoricalFigure, position: Int) {
        val font = settingController.state.getTextFont(binding.root.context)
        val size = settingController.state.getTextSize(binding.root.context)
        binding.tvName.typeface = font
        binding.tvDate.typeface = font
        binding.tvName.textSize = size
        binding.tvDate.textSize = size
        binding.tvName.setTextColor(settingController.state.getTextColor(binding.tvName.context))
        binding.tvDate.setTextColor(settingController.state.getTextColor(binding.tvDate.context))

        binding.root.setOnClickListener {
            listener.onClickItem(item, ItemAction.LONG_CLICK)
        }

        binding.tvDate.text = "(${item.birthYear} - ${item.deathDate})"
        binding.tvName.text = item.name ?: "Không rõ"

        binding.imgAvatar.setColorFilter(
            ContextCompat.getColor(binding.root.context, R.color.primary),
            PorterDuff.Mode.SRC_IN)
        binding.imgAvatar.setPadding(24, 24, 24, 24)

        binding.imgAvatar.show(
            url = item.image,
            placeholder = R.drawable.icon_user,
            scaleType = CenterCrop(),
            onLoadFailed = {
                binding.imgAvatar.setColorFilter(
                    ContextCompat.getColor(binding.root.context, R.color.primary),
                    PorterDuff.Mode.SRC_IN
                )
                binding.imgAvatar.setPadding(24, 24, 24, 24)
            },
            onLoadSuccess = {
                binding.imgAvatar.clearColorFilter()
                binding.imgAvatar.setPadding(0, 0, 0, 0)
            }
        )

    }
}