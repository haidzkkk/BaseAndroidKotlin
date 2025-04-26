package com.app.motel.feature.handleBill.viewmodel

import android.icu.util.Calendar
import androidx.lifecycle.MutableLiveData
import com.app.motel.common.service.DateConverter
import com.app.motel.common.service.DateConverter.toCalendar
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.entity.HoaDonEntity
import com.app.motel.data.model.Bill
import com.app.motel.data.model.Resource

class HandleBillState: AppViewLiveData {
    val filterState = MutableLiveData(HoaDonEntity.STATUS_PAID)
    val currentDate = MutableLiveData(DateConverter.getCurrentDateTime().toCalendar())
    val bills = MutableLiveData<Resource<List<Bill>>>()

    val currentBill = MutableLiveData<Bill?>()

    val getListBillByFilter: List<Bill> get() = (bills.value?.data ?: arrayListOf()).filter{
        it.status == filterState.value
        && it.month == currentDate.value!!.get(Calendar.MONTH) + 1
        && it.year == currentDate.value!!.get(Calendar.YEAR)
    }
}