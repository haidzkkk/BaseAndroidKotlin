package com.app.langking.ultis

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateConverter {
    private const val DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"
    fun getCurrentDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            currentDateTime.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        } else {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            dateFormat.format(calendar.time)
        }
    }

    fun getDaysDifference(date1: String?, date2: String?): Long {
        if(date1.isNullOrEmpty() || date2.isNullOrEmpty()) return 0;
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
            val firstDate = LocalDate.parse(date1 ?: getCurrentDateTime(), formatter)
            val secondDate = LocalDate.parse(date2 ?: getCurrentDateTime(), formatter)
            kotlin.math.abs(java.time.temporal.ChronoUnit.DAYS.between(firstDate, secondDate))
        } else {
            val dateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            val firstDate = dateFormat.parse(date1 ?: getCurrentDateTime())!!
            val secondDate = dateFormat.parse(date2 ?: getCurrentDateTime())!!
            val diff = kotlin.math.abs(firstDate.time - secondDate.time)
            TimeUnit.MILLISECONDS.toDays(diff)
        }
    }
}