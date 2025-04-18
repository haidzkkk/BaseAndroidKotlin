package com.app.motel.common.service

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
    private const val PATTERN_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    private const val LOCAL_DATE_FORMAT = "dd/MM/yyyy"


    fun getCurrentDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            currentDateTime.format(DateTimeFormatter.ofPattern(PATTERN_DATE_FORMAT))
        } else {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault())
            dateFormat.format(calendar.time)
        }
    }

    fun getDaysDifference(date1: String?, date2: String?): Long {
        if(date1.isNullOrEmpty() || date2.isNullOrEmpty()) return 0;
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(PATTERN_DATE_FORMAT)
            val firstDate = LocalDate.parse(date1 ?: getCurrentDateTime(), formatter)
            val secondDate = LocalDate.parse(date2 ?: getCurrentDateTime(), formatter)
            kotlin.math.abs(java.time.temporal.ChronoUnit.DAYS.between(firstDate, secondDate))
        } else {
            val dateFormat = SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault())
            val firstDate = dateFormat.parse(date1 ?: getCurrentDateTime())!!
            val secondDate = dateFormat.parse(date2 ?: getCurrentDateTime())!!
            val diff = kotlin.math.abs(firstDate.time - secondDate.time)
            TimeUnit.MILLISECONDS.toDays(diff)
        }
    }

    fun areDatesClose(dates: List<String>): Boolean {
        if (dates.size < 2) return true

        val dateTimes: List<Long> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(PATTERN_DATE_FORMAT)
            dates.map { LocalDateTime.parse(it, formatter).atZone(java.time.ZoneId.systemDefault()).toEpochSecond() }
        } else {
            val formatter = SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault())
            dates.map { formatter.parse(it)!!.time / 1000 }
        }.sorted()

        for (i in 0 until dateTimes.size - 1) {
            val durationMinutes = TimeUnit.SECONDS.toMinutes(dateTimes[i + 1] - dateTimes[i])
            if (durationMinutes > 60) return false
        }
        return true
    }

    fun localStringToDate(dateString: String): Date?{
        val format = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.getDefault())
        return try{
            format.parse(dateString)
        }catch (e: Exception){
            null
        }
    }

    fun dateToLocalString(date: Date): String {
        val format = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.getDefault())
        return format.format(date)
    }

}