package com.app.motel.ultis

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.core.content.ContentProviderCompat.requireContext
import java.text.Normalizer

// ex: period = 2213 TCN - 253 TCN
fun getFromYear(period: String?): Int? {
    if (period.isNullOrEmpty()) return null
    val elements = period.split(" ")
    val from = elements.getOrNull(0)?.toIntOrNull()
    val isNegative = elements.getOrNull(1) == "TCN"
    return if (from != null) {
        if (isNegative) -from else from
    } else null
}

fun getToYear(period: String?): Int? {
    if (period.isNullOrEmpty()) return null

    val parts = period.split("-").map { it.trim() }
    if (parts.size < 2) return null

    val toPart = parts[1] // "253 TCN"
    val toElements = toPart.split(" ")

    val toYear = toElements.firstOrNull { it.toIntOrNull() != null }?.toIntOrNull()
    val isTCN = toElements.any { it.equals("TCN", ignoreCase = true) }

    return if (toYear != null) {
        if (isTCN) -toYear else toYear
    } else null
}