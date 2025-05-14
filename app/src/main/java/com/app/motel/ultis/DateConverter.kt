package com.history.vietnam.ultis

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit


object DateConverter {
    private const val PATTERN_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    private const val LOCAL_DATE_FORMAT = "dd/MM/yyyy"
    private const val LOCAL_DATE_FORMAT2 = "HH:mm - dd/MM/yyyy"

    fun getCurrentDateTime(): Date {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant())
        } else {
            val calendar = Calendar.getInstance()
            calendar.time
        }
    }

    fun getCurrentStringDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            currentDateTime.format(DateTimeFormatter.ofPattern(PATTERN_DATE_FORMAT))
        } else {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault())
            dateFormat.format(calendar.time)
        }
    }

    fun getCurrentLocalDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentDateTime = LocalDateTime.now()
            currentDateTime.format(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT))
        } else {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.getDefault())
            dateFormat.format(calendar.time)
        }
    }

    fun getDaysDifference(date1: String?, date2: String?): Long {
        if(date1.isNullOrEmpty() || date2.isNullOrEmpty()) return 0;
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(PATTERN_DATE_FORMAT)
            val firstDate = LocalDate.parse(date1 ?: getCurrentStringDateTime(), formatter)
            val secondDate = LocalDate.parse(date2 ?: getCurrentStringDateTime(), formatter)
            kotlin.math.abs(java.time.temporal.ChronoUnit.DAYS.between(firstDate, secondDate))
        } else {
            val dateFormat = SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault())
            val firstDate = dateFormat.parse(date1 ?: getCurrentStringDateTime())!!
            val secondDate = dateFormat.parse(date2 ?: getCurrentStringDateTime())!!
            val diff = kotlin.math.abs(firstDate.time - secondDate.time)
            TimeUnit.MILLISECONDS.toDays(diff)
        }
    }


    fun stringToDate(dateString: String?): Date?{
        val format = SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault())
        return try{
            format.parse(dateString!!)
        }catch (e: Exception){
            null
        }
    }

    fun localStringToDate(dateString: String?): Date?{
        val format = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.getDefault())
        return try{
            format.parse(dateString!!)
        }catch (e: Exception){
            null
        }
    }

    fun dateToLocalString(date: Date): String {
        val format = SimpleDateFormat(LOCAL_DATE_FORMAT, Locale.getDefault())
        return format.format(date)
    }

    fun dateToLocalString2(date: Date): String {
        val format = SimpleDateFormat(LOCAL_DATE_FORMAT2, Locale.getDefault())
        return format.format(date)
    }

    fun monthsBetweenDates(date1: Date, date2: Date): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            val localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            ChronoUnit.MONTHS.between(localDate1, localDate2).toInt()
        } else {
            val cal1 = Calendar.getInstance().apply { time = date1 }
            val cal2 = Calendar.getInstance().apply { time = date2 }

            val yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)
            val monthDiff = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH)

            yearDiff * 12 + monthDiff
        }
    }

    fun addMonthsToDate(date: Date, monthsToAdd: Int): Date {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            val updatedDate = localDate.plusMonths(monthsToAdd.toLong())
            Date.from(updatedDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        } else {
            val calendar = Calendar.getInstance().apply { time = date }
            calendar.add(Calendar.MONTH, monthsToAdd)
            calendar.time
        }
    }

    fun calculateMonth(date: Date, monthCountToCalculate: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, monthCountToCalculate)
        return calendar
    }

    fun Date.toCalendar(): Calendar{
        val date = this
        return Calendar.getInstance().apply {time = date}
    }

    fun getTimeAgo(timeString: String?): String {
        try {
            if(timeString.isNullOrEmpty()) return "Không rõ"

            val format = SimpleDateFormat(PATTERN_DATE_FORMAT, Locale.getDefault())
            format.timeZone = TimeZone.getDefault()
            val time = format.parse(timeString) ?: return "Không rõ"
            val now = Date()

            val diff = now.time - time.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            val weeks = days / 7
            val months = days / 30
            val years = days / 365

            return when {
                seconds < 60 -> "vừa xong"
                minutes < 60 -> "$minutes phút"
                hours < 24 -> "$hours giờ"
                days < 7 -> "$days ngày"
                weeks < 4 -> "$weeks tuần"
                months < 12 -> "$months tháng"
                else -> "$years năm"
            }
        } catch (e: Exception) {
            return "Lỗi định dạng thời gian"
        }
    }

}
