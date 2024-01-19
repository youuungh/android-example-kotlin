package com.example.layout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val width1 = resources.displayMetrics.widthPixels / 4 * 3
        binding.tv1.layoutParams = LinearLayout.LayoutParams(width1, LinearLayout.LayoutParams.MATCH_PARENT)
        binding.tv1.text = "Text 1"
        binding.tv1.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.md_light_green_50))

        val width2 = resources.displayMetrics.widthPixels / 4
        binding.tv2.layoutParams = LinearLayout.LayoutParams(width2, LinearLayout.LayoutParams.MATCH_PARENT)
        binding.tv2.text = "Text 2"
        binding.tv2.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.md_lime_A100))
    }
}