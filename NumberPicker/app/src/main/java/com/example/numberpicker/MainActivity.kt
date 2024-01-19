package com.example.numberpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numberpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PickerDialog.PickerDialogListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setTime.setOnClickListener {
            val dlg = PickerDialog(this@MainActivity, 0, 0)
            dlg.show(supportFragmentManager, "PickerDialog")
        }
    }

    override fun onDoneClicked(ampm: Int, time: Int) {
        val meridiem = if (ampm == 0) "오전" else "오후"

        binding.tvTime.text = "$meridiem ${time}시"
    }
}