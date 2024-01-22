package com.example.coil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import com.example.coil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // https://coil-kt.github.io/coil/

        binding.iv1.load(R.drawable.cyberpunk1) {
            //placeholder()
            //error()
            //size()
            //transformations(CircleCropTransformation())
        }

        binding.button.setOnClickListener {
            binding.iv2.load(R.drawable.cyberpunk2) {
                crossfade(200)
                transformations(CircleCropTransformation())
            }
        }
    }
}