package com.example.expandablerecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expandablerecycler.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        binding.tvContent.text = title
    }
}