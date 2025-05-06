package com.app.motel.ultis

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.app.motel.data.model.Resource
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.Normalizer
import java.util.Locale
import java.util.regex.Pattern

inline fun <T> LiveData<Resource<T>>.observe(
    owner: LifecycleOwner,
    crossinline onChanged: (Resource<T>) -> Unit
) {
    observe(owner) { resource ->
        resource.getDataIfNotHandled()?.let { onChanged(it) }
    }
}

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: Observer<T>) {
    observe(owner, object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }
    })
}