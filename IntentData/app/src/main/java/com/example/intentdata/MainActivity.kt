package com.example.intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentdata.databinding.ActivityMainBinding
import com.example.model.Player

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.data.setOnClickListener {
            var intent = Intent(applicationContext, MainActivity2::class.java)
            intent.putExtra("name", "손흥민")
            intent.putExtra("age", 1992)
            startActivity(intent)
        }
        binding.classData.setOnClickListener {
            var intent =  Intent(applicationContext, MainActivity2::class.java)
            intent.putExtra("player", Player("손흥민", 1992, "토트넘"))
            startActivity(intent)
        }
    }
}