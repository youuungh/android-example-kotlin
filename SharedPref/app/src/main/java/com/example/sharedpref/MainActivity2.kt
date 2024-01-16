package com.example.sharedpref

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpref.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    lateinit var option: SharedPreferences
    lateinit var userInfo: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        option = getSharedPreferences("OPTION", MODE_PRIVATE)
        userInfo = getSharedPreferences("USER_INFO", MODE_PRIVATE)

        var str = """이름: ${userInfo.getString("name", "null")}
            |
            |스위치: ${valueToString(option.getBoolean("switch_value", false))}
        """.trimMargin()

        binding.result.text = str
    }

    private fun valueToString(checkBoolean: Boolean): String {
        return when(checkBoolean) {
            true -> "ON"
            else -> "OFF"
        }
    }
}