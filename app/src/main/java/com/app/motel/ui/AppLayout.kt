package com.history.vietnam.ui

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.history.vietnam.core.AppBaseDialog
import com.history.vietnam.databinding.DialogConfirmBinding
import com.history.vietnam.databinding.DialogLoadingBinding

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

fun showLoadingDialog(
    context: Context,
    layoutInflater: LayoutInflater,
    content: String? = null,
    isCancelable: Boolean = false
): Dialog {
    val dialog = AppBaseDialog.Builder(context, DialogLoadingBinding.inflate(layoutInflater))
        .isWidthMatchParent(true)
        .build()

    dialog.show()
    dialog.setCancelable(isCancelable)

    dialog.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)


    val paramsProcessIndicator = dialog.binding.processIndicator.layoutParams as ViewGroup.MarginLayoutParams
    val paramsTvContent = dialog.binding.tvContent.layoutParams as ViewGroup.MarginLayoutParams

    if (content != null) {
        dialog.binding.tvContent.text = content
        paramsTvContent.setMargins(0, 150, 0, 0)
    } else {
        dialog.binding.tvContent.isVisible = false
        paramsTvContent.setMargins(0, 0, 0, 0)
    }

    dialog.binding.processIndicator.layoutParams = paramsProcessIndicator
    dialog.binding.tvContent.layoutParams = paramsTvContent

    return dialog
}
