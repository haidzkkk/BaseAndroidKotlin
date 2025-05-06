package com.app.motel.ui

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.app.motel.core.AppBaseDialog
import com.app.motel.databinding.DialogConfirmBinding
import com.app.motel.databinding.DialogLoadingBinding

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showDialogConfirm(
    title: String,
    content: String?,
    buttonConfirm: String = "Có",
    buttonCancel: String = "Hủy",
    confirm: (() -> Unit) = {},
    cancel: (() -> Unit) = {}
){
    val dialog = AppBaseDialog
        .Builder(this, DialogConfirmBinding.inflate(LayoutInflater.from(this)))
        .build()
    dialog.show()

    dialog.binding.apply {
        tvTitle.text = title
        tvContent.text = content
        tvConfirm.text = buttonConfirm
        tvCancel.text = buttonCancel

        tvContent.isVisible = !content.isNullOrEmpty()

        tvConfirm.setOnClickListener {
            confirm()
            dialog.dismiss()
        }
        tvCancel.setOnClickListener {
            cancel()
            dialog.dismiss()
        }
    }
}

fun showLoadingDialog(context: Context, layoutInflater: android.view.LayoutInflater, content: String? = null, isCancelable: Boolean = false): Dialog {
    val dialog = AppBaseDialog.Builder(context, DialogLoadingBinding.inflate(layoutInflater))
        .isWidthMatchParent(false)
        .build()
    dialog.show()
    dialog.setCancelable(isCancelable)

    val paramsProcessIndicator = dialog.binding.processIndicator.layoutParams as ViewGroup.MarginLayoutParams
    val paramsTvContent = dialog.binding.tvContent.layoutParams as ViewGroup.MarginLayoutParams
    if(content != null){
        dialog.binding.tvContent.text = content
        paramsProcessIndicator.setMargins(300, 300, 300, 150)
        paramsTvContent.setMargins(0, 0, 0, 150)

    }else{
        dialog.binding.tvContent.isVisible = false
        paramsProcessIndicator.setMargins(300, 300, 300, 300)
        paramsTvContent.setMargins(0, 0, 0, 0)
    }
    dialog.binding.processIndicator.layoutParams = paramsProcessIndicator
    dialog.binding.tvContent.layoutParams = paramsTvContent

    return dialog
}