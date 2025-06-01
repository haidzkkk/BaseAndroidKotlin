package com.app.langking.ultis

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateConverter {
    private const val DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"

    fun getCurrentDate(): Date {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant())
        } else {
            val calendar = Calendar.getInstance()
            calendar.time
        }
    }

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

    fun areDatesClose(dates: List<String>): Boolean {
        if (dates.size < 2) return true

        val dateTimes: List<Long> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
            dates.map { LocalDateTime.parse(it, formatter).atZone(java.time.ZoneId.systemDefault()).toEpochSecond() }
        } else {
            val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            dates.map { formatter.parse(it)!!.time / 1000 }
        }.sorted()

        for (i in 0 until dateTimes.size - 1) {
            val durationMinutes = TimeUnit.SECONDS.toMinutes(dateTimes[i + 1] - dateTimes[i])
            if (durationMinutes > 60) return false
        }
        return true
    }
}