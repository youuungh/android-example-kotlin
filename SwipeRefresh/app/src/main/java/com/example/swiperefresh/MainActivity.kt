package com.example.swiperefresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swiperefresh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemAdapter = ItemAdapter()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
            setHasFixedSize(true)
        }

        binding.refresh.setOnRefreshListener {
            itemAdapter!!.notifyDataSetChanged()
            Toast.makeText(applicationContext, "새로고침", Toast.LENGTH_SHORT).show()
            binding.refresh.isRefreshing = false
        }

        binding.add.setOnClickListener {
            Toast.makeText(applicationContext, "추가됨", Toast.LENGTH_SHORT).show()
            itemAdapter.addItem()
        }

        binding.remove.setOnClickListener {
            Toast.makeText(applicationContext, "삭제됨", Toast.LENGTH_SHORT).show()
            itemAdapter.removeItem()
        }
    }
}