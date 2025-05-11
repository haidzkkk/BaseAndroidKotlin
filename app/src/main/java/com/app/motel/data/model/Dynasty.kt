package com.app.motel.data.model

object Dynasty {
    fun getDynasty(yearStr: String?): Pair<String, String>{
        val year = extractYears(yearStr)

        return when(year){
            in Int.MIN_VALUE..-179 -> Pair<String, String>("Thời kỳ dựng nước", "khoảng thế kỷ 7 TCN - 179 TCN")
            in -179..938 -> Pair<String, String>("Thời kỳ Bắc thuộc", "179 TCN - 938")
            in 939 ..1883 -> Pair<String, String>("Thời kỳ độc lập tự chủ", "939 - 1883")
            in 1884 ..1945 -> Pair<String, String>("Thời kỳ Pháp thuộc", "1884 - 1945")
            in 1945 ..1975 -> Pair<String, String>("Thời kỳ kháng chiến", "1945 - 1975")
            in 1976 .. Int.MAX_VALUE -> Pair<String, String>("Thời kỳ thống nhất", "1976 đến nay")
            else -> Pair<String, String>("Không rõ", ".. - ..")
        }
    }

    // to extract year from string: KHoảng 634 TCN - 1932
    private fun extractYears(str: String?): Int? {
        val strs = str?.split(" ")
        var year = strs?.map { it.toIntOrNull() }?.find { it != null }

        if (year != null && str?.contains("thế kỷ", ignoreCase = true) == true) year *= 100
        if (year != null && str?.contains("TCN", ignoreCase = true) == true) year *= -1
        return year
    }
}