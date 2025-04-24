package com.app.motel.feature.room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.motel.core.AppBaseAdapter
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant
import com.app.motel.databinding.ItemTenantBinding

class RoomTenantAdapter(
    val listener: AppBaseAdapter.AppListener<Tenant>
): AppBaseAdapter<Tenant, ItemTenantBinding>() {
    private var currentRoom: Room? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateCurrentRoom(room: Room?) {
        currentRoom = room
        notifyDataSetChanged()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemTenantBinding {
        return ItemTenantBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemTenantBinding, item: Tenant, position: Int) {
        binding.tvRoomName.text = currentRoom?.roomName
        binding.tvName.text = "Họ tên: \t${item.fullName}"
        binding.tvNumberPhone.text = "SĐT: \t${item.phoneNumber ?: ""}"

        binding.cbRenting.isChecked = true
        binding.cbRentingMain.isChecked = item.id == currentRoom?.contract?.customerId

        binding.root.setOnClickListener {
            listener.onClickItem(item, ItemAction.CLICK)
        }
    }
}