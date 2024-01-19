package com.example.imagetransition

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.imagetransition.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iv1.setOnClickListener {
            clickEvent(it, 1)
        }
        binding.iv2.setOnClickListener {
            clickEvent(it, 2)
        }
        binding.iv3.setOnClickListener {
            clickEvent(it, 3)
        }
    }

    private fun clickEvent(view: View, pos: Int) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra("pos", pos)
        val option = ActivityOptions.makeSceneTransitionAnimation(this, view, "ImageTransition")
        startActivity(intent, option.toBundle())
    }
}