package com.example.calendar

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.example.calendar.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cal = Calendar.getInstance()

        binding.calendarButton.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { datePicker, year, month, day ->
                binding.tvDate.text = "${year}년 ${month+1}월 ${day}일"
            },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )

            // Calendar 날짜 제한
            datePickerDialog.datePicker.minDate = cal.timeInMillis
            datePickerDialog.show()
        }
        binding.timeButton.setOnClickListener {
            TimePickerDialog(this, { timePicker, hour, minute ->
                binding.tvTime.text = "${hour}시 ${minute}분"
            },
                cal.get(Calendar.HOUR),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }
}