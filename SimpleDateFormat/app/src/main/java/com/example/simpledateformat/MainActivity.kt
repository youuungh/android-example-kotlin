package com.example.simpledateformat

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpledateformat.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendar.setOnDateChangeListener { _, y, m, d ->
            val calendar = Calendar.getInstance()
            calendar.set(y, m, d)
            val str = calendarToString(calendar.time, "제목", ++num)
            binding.tvTest.text = str
        }
    }

    private fun calendarToString(date: Date, title: String, num: Int): String {
        val format = SimpleDateFormat("yyyy.MM.dd")
        return "${format.format(date)}/${title}/$num"
    }
}