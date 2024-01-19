package com.example.numberpicker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.example.numberpicker.databinding.PickerDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PickerDialog(
    pickerDialogListener: PickerDialogListener,
    ampm: Int,
    time: Int
): BottomSheetDialogFragment() {
    private lateinit var binding: PickerDialogBinding
    private var ampmArray = arrayOf("오전", "오후")
    private var timeArray = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
    private var pickerDialogListener: PickerDialogListener? = null
    private var ampm: Int? = null
    private var time: Int? = null

    interface PickerDialogListener {
        fun onDoneClicked(ampm: Int, time: Int)
    }

    init {
        this.ampm = ampm
        this.time = time
        this.pickerDialogListener = pickerDialogListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PickerDialogBinding.inflate(inflater, container, false)
        val ampmPicker = binding.ampmPicker
        val timePicker = binding.timePicker
        val tvDone = binding.tvDone

        tvDone.setOnClickListener {
            ampm = ampmPicker.value
            time = timePicker.value

            this.pickerDialogListener?.onDoneClicked(ampm!!, time!!)
            dismiss()
        }

        ampmPicker.wrapSelectorWheel = false
        ampmPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        ampmPicker.minValue = 0
        ampmPicker.maxValue = ampmArray.size - 1
        timePicker.minValue = 0
        timePicker.maxValue = 12

        ampmPicker.displayedValues = ampmArray
        timePicker.displayedValues = timeArray

        ampmPicker.value = ampm!!
        timePicker.value = time!!

        return binding.root
    }
}