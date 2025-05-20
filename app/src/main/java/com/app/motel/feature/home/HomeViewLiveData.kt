package com.history.vietnam.feature.Home

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.PageInfo
import com.app.motel.ultis.getFromYear
import com.app.motel.ultis.getToYear
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Resource

open class HomeViewLiveData : AppState {
    val searchInfo = MutableLiveData<Resource<List<PageInfo>>>()
    val formYear = MutableLiveData<Int>(null)
    val toYear = MutableLiveData<Int>(null)
    val typeSort = MutableLiveData<Sort>(Sort.DATE_ASC)
    val filterListSearchInfo: List<PageInfo>
        get() = searchInfo.value?.data
            ?.asSequence()
//             Lọc theo năm từ formYear và toYear nếu đã chọn
            ?.filter { info ->
                val from = formYear.value
                val to = toYear.value

                val pageFrom = info.year?.toIntOrNull() ?: getFromYear(info.year)
                val pageTo = getToYear(info.year)

                val isWithinFrom = from == null || (pageFrom != null && pageFrom >= from)
                val isWithinTo = to == null || (pageTo != null && pageTo <= to)

                isWithinFrom && isWithinTo
            }
            // Sắp xếp theo Sort
            ?.sortedWith(compareBy<PageInfo> {
                when (typeSort.value) {
                    Sort.DATE_ASC -> {
                        val pageFrom = it.year?.toIntOrNull() ?: getFromYear(it.year)
                        pageFrom ?: Int.MAX_VALUE
                    }
                    Sort.DATE_DESC -> {
                        val pageFrom = it.year?.toIntOrNull() ?: getFromYear(it.year)
                        -(pageFrom ?: Int.MIN_VALUE)
                    }
                    else -> 0
                }
            })
            ?.toList()
            ?: emptyList()

    enum class Sort(val value: String){
        DATE_ASC("Năm tăng"),
        DATE_DESC("Năm giảm"),;

        fun next(): Sort {
            return when (this) {
                DATE_ASC -> DATE_DESC
                DATE_DESC -> DATE_ASC
            }
        }
    }
}