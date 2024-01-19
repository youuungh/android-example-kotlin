package com.example.chip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            Toast.makeText(this, "클릭됨$checkedIds", Toast.LENGTH_SHORT).show()
        }
    }
}