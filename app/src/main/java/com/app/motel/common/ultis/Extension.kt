package com.app.motel.common.ultis

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: Observer<T>) {
    observe(owner, object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }
    })
}

fun String.require(): CharSequence{
    return SpannableStringBuilder().apply {
        append(this@require)
        append(" *", ForegroundColorSpan(Color.RED), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

fun String?.toMoney(): Int{
    return this
        ?.replace(",", "")
        ?.replace(" ", "")
        ?.trim()
        ?.toIntOrNull() ?: 0
}

fun String?.toStringMoney(): String{
    val money = this.toMoney()
    if(money == 0) return this ?: ""

    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = ' '
    }
    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(money)
}

fun Int?.toStringMoney(): String{
    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = ' '
    }
    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(this)
}

fun Int.formatRoomName(): String {
    return if (this < 100) {
        (this + 100).toString()
    } else {
        this.toString()
    }
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}