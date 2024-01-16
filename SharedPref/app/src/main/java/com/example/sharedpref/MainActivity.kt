package com.example.sharedpref

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.example.sharedpref.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var option: SharedPreferences
    lateinit var userInfo: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        option = getSharedPreferences("OPTION", MODE_PRIVATE)
        userInfo = getSharedPreferences("USER_INFO", MODE_PRIVATE)

        binding.nextButton.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        binding.saveButton.setOnClickListener {
            userInfo.edit {
                putString("name", binding.edtName.text.toString())
                binding.edtName.setText("")
            }
            Toast.makeText(applicationContext, "저장됨", Toast.LENGTH_SHORT).show()
        }
        binding.mSwitch.setOnCheckedChangeListener { _, _ ->
            option.edit { putBoolean("switch_value", binding.mSwitch.isChecked) }
        }
    }

    private fun savedData() {
        binding.edtName.setText(userInfo.getString("name", ""))
        binding.mSwitch.isChecked = option.getBoolean("switch_value", false)
    }

    override fun onStart() {
        super.onStart()
        savedData()
    }
}