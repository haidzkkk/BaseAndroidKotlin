package com.app.motel.ui

import android.content.Context
import android.view.LayoutInflater
import com.history.vietnam.core.AppBaseDialog
import com.history.vietnam.databinding.DialogDatePickerBinding

object PickDate {

    fun pickFormToYear(context: Context, from: Int? , to: Int?, onConfirm: (Int?, Int?) -> Unit, onRemove: (Int?, Int?) -> Unit){
        val dialog = AppBaseDialog.Builder(context, DialogDatePickerBinding.inflate(LayoutInflater.from(context)))
            .build()
        dialog.show()

        var formYear = from ?: 0
        var toYear = to ?: 0

        val yearStep = 100
        val minYear = -2000
        val maxYear = 2020
        val years = (minYear..maxYear step yearStep).toList()

        val pickerFrom = dialog.binding.pickerFrom
        val pickerTo = dialog.binding.pickerTo

        pickerFrom.minValue = 0
        pickerFrom.maxValue = years.size - 1
        pickerFrom.displayedValues = years.map { it.toString() }.toTypedArray()
        pickerFrom.value = years.indexOf(formYear).coerceAtLeast(0)

        pickerTo.minValue = 0
        pickerTo.maxValue = years.size - 1
        pickerTo.displayedValues = years.map { it.toString() }.toTypedArray()
        pickerTo.value = years.indexOf(toYear).coerceAtLeast(0)

        pickerFrom.setOnValueChangedListener { _, _, newVal ->
            formYear = years[newVal]
        }

        pickerTo.setOnValueChangedListener { _, _, newVal ->
            toYear = years[newVal]
        }

        dialog.binding.tvRemove.setOnClickListener {
            onRemove(formYear, toYear)
            dialog.dismiss()
        }

        dialog.binding.tvConfirm.setOnClickListener {
            onConfirm(formYear, toYear)
            dialog.dismiss()
        }
    }
}