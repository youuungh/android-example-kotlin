package com.example.alertdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.alertdialog.databinding.CustomDialogBinding

class CustomDialog() : DialogFragment() {
    private var _binding : CustomDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var buttonClickListener: OnButtonClickListener

    interface OnButtonClickListener {
        fun onButtonClicked()
    }

    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CustomDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.negativeButton.setOnClickListener {
            dismiss()
        }
        binding.positiveButton.setOnClickListener {
            buttonClickListener.onButtonClicked()
            dismiss()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}