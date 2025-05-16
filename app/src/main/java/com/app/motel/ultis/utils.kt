package com.app.motel.ultis

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContentProviderCompat.requireContext
import java.text.Normalizer


fun String.removeAccents(): String {
    val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalized.replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
        .lowercase()
}

fun String?.containsRemoveAccents(other: String?, ignoreCase: Boolean = false): Boolean{
    if(this.isNullOrEmpty() || other.isNullOrEmpty()) return false
    return this.removeAccents().contains(other.removeAccents(), ignoreCase)
}

fun View.focus(){
    this.requestFocus()
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}