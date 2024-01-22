package com.example.recyclerview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemAdapter = ItemAdapter()

        binding.loading.show()

        Handler(Looper.getMainLooper()).postDelayed({
            binding.recycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = itemAdapter
                setHasFixedSize(true)
                addItemDecoration(ItemDecoration())
            }
            binding.loading.hide()
        }, 2000)


        binding.fab.setOnClickListener {
            binding.recycler.smoothScrollToPosition(0)
        }

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (binding.recycler.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.fab.show()
                } else {
                    binding.fab.hide()
                }

                if (!binding.recycler.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Toast.makeText(applicationContext, "최하단입니다", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}