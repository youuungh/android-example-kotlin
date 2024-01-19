package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val person = Person("손흥민", 31)
        binding.p1 = person
    }
}