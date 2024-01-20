package com.example.transparentstatusbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.transparentstatusbar.databinding.ActivityMainBinding
import com.example.utils.fitSystemWindowsWithAdjustResize

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.fitSystemWindowsWithAdjustResize()
    }
}