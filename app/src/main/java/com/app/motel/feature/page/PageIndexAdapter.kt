package com.app.motel.feature.page

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.app.motel.data.model.Section
import com.app.motel.feature.setting.SettingController
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemFigureContentBinding

class PageIndexAdapter constructor(
    private val listener: AppBaseAdapter.AppListener<Int>,
    private val settingController: SettingController? = null,
): AppBaseAdapter<Section, ItemFigureContentBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemFigureContentBinding {
         return ItemFigureContentBinding.inflate(inflater, parent, false)
    }

    private var currentPosition = 0
    @SuppressLint("NotifyDataSetChanged")
    fun setCurrentPosition(position: Int){
        currentPosition = position
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ItemFigureContentBinding, item: Section, position: Int) {
        binding.root.setOnClickListener {
            setCurrentPosition(position)
            listener.onClickItem(position)
        }

        binding.tvTitle.apply {
            when{
                item.isLevel1 -> {
                    setTypeface(null, Typeface.BOLD)
                    textSize = 20f
                }
                item.isLevel2 -> {
                    textSize = 18f
                    setTypeface(null, Typeface.NORMAL)
                }
                item.isLevel3 -> {
                    textSize = 15f
                    setTypeface(null, Typeface.ITALIC)
                }
            }

            if(position == currentPosition){
                setTypeface(null, Typeface.BOLD)
                setTextColor(binding.root.context.getColor(com.history.vietnam.R.color.primary))
            }else{
                settingController?.state?.getTextColor(binding.root.context).apply {
                    setTextColor(this ?: binding.root.context.getColor(com.history.vietnam.R.color.textColor1))
                }
            }
            typeface = settingController?.state?.getTextFont(binding.root.context)

            text = item.title
        }

        binding.tvContent.apply {
            isVisible = !item.content.isNullOrEmpty()
            settingController?.state?.getHintTextColor(binding.root.context).apply {
                setTextColor(this ?: binding.root.context.getColor(com.history.vietnam.R.color.textColor2))
            }
            typeface = settingController?.state?.getTextFont(binding.root.context)

            text = item.content
        }
    }
}