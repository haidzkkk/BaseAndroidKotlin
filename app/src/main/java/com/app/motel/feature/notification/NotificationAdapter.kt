package com.app.motel.feature.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.app.motel.data.model.AppNotification
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.databinding.ItemNotificationBinding
import com.history.vietnam.ultis.DateConverter

class NotificationAdapter(
    private val listener: AppListener<AppNotification>
): AppBaseAdapter<AppNotification, ItemNotificationBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemNotificationBinding {
         return ItemNotificationBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemNotificationBinding, item: AppNotification, position: Int) {
        binding.tvTitle.text = item.message
        binding.tvTime.text = DateConverter.getTimeAgo(item.time)
        binding.iconReaded.isVisible = item.read == false
        binding.root.setOnClickListener {
            listener.onClickItem(item, ItemAction.CLICK)
        }
    }
}