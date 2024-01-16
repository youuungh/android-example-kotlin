package com.example.intentdata

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.intentdata.databinding.ActivityMain2Binding
import com.example.model.Player
import java.io.Serializable

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("name")) {
            binding.getData.text =
                "이름: ${intent.getStringExtra("name")}, 출생: ${intent.getIntExtra("age", 0)}"
        }

        if (intent.hasExtra("player")) {
            binding.getData.text =
                intent.getParcelableExtra("player", Player::class.java).toString()
        }
    }
}