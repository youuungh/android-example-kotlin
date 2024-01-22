package com.example.expandablelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.expandablelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvLayout.setOnClickListener {
            if (binding.cvContentLayout.isVisible) {
                binding.cvContentLayout.isVisible = false
                binding.button.animate().apply {
                    duration = 200L
                    rotation(0f)
                }
            } else {
                binding.cvContentLayout.isVisible = true
                binding.button.animate().apply {
                    duration = 200L
                    rotation(180f)
                }
            }
        }
    }
}