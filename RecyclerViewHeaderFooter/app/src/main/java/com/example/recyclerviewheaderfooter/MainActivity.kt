package com.example.recyclerviewheaderfooter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewheaderfooter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = arrayListOf<String>()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ItemAdapter(itemList)
            setHasFixedSize(true)
        }
    }
}