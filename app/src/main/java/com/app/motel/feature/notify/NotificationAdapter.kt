package com.app.motel.feature.notify

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.motel.common.service.DateConverter
import com.app.motel.core.AppBaseAdapter
import com.app.motel.data.model.Complaint
import com.app.motel.databinding.ItemComplaintBinding

class NotificationAdapter: AppBaseAdapter<Complaint, ItemComplaintBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemComplaintBinding {
        return ItemComplaintBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ItemComplaintBinding, item: Complaint, position: Int) {
        binding.tvRoomName.text = "Phòng: ${item.room?.roomName ?: ""}"
        binding.tvStatus.text = item.status

        binding.tvComplaintUserName.text = item.tenant?.fullName
        binding.tvTitle.text = item.title
        binding.tvContent.text = item.content
        binding.tvCreateDate.text = DateConverter.stringToDate(item.createdDate ?: "")?.let {
           DateConverter.dateToLocalString2(it)
        }
        binding.tvState.text = item.status
    }
}