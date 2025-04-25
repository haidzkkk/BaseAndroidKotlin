package com.app.motel.feature.tenant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.motel.core.AppBaseAdapter
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant
import com.app.motel.databinding.ItemTenantBinding

class TenantAdapter(
    val listener: AppBaseAdapter.AppListener<Tenant>
): AppBaseAdapter<Tenant, ItemTenantBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemTenantBinding {
        return ItemTenantBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemTenantBinding, item: Tenant, position: Int) {
        binding.tvRoomName.text = item.room?.roomName
        binding.tvName.text = "Họ tên: \t${item.fullName}"
        binding.tvNumberPhone.text = "SĐT: \t${item.phoneNumber ?: "Không có"}"

        binding.cbRenting.isChecked = item.room != null
        binding.cbRentingMain.isChecked = item.id == item.contract?.customerId

        binding.root.setOnClickListener {
            listener.onClickItem(item, ItemAction.CLICK)
        }
    }
}