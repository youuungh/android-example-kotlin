package com.example.imagetransition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imagetransition.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = when(intent.getIntExtra("pos", 1)) {
            1 -> R.drawable.cyberpunk1
            2 -> R.drawable.cyberpunk2
            else -> R.drawable.cyberpunk3
        }
        binding.ivFull.setImageResource(image)
        binding.ivFull.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}