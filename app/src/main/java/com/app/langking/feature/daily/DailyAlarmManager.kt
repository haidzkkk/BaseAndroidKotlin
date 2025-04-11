package com.app.langking.feature.daily

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.app.langking.ultis.AppConstants
import java.util.Calendar

object DailyAlarmManager {
    fun scheduleAlarm(context: Context) {
        val sharedPref = context.getSharedPreferences(AppConstants.prefsKey, Context.MODE_PRIVATE)
        val isAlarmSet = sharedPref.getBoolean(AppConstants.isDailyNotify, false)

        if (isAlarmSet) return

        val intent = Intent(context, DailyAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 6)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        sharedPref.edit().putBoolean(AppConstants.isDailyNotify, true).apply()
    }

}