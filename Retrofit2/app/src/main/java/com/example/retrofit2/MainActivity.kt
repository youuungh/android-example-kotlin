package com.example.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private var coinList = listOf<Coin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemAdapter = ItemAdapter()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
            setHasFixedSize(true)
        }

        binding.update.setOnClickListener {
            itemAdapter.setCoinList(coinList)
            itemAdapter.notifyDataSetChanged()
        }

        init()
    }

    private fun init() {
        val call = RetrofitClient.getRetrofitService.getAllCoin()
        call.enqueue(object : Callback<List<Coin>> {
            override fun onResponse(call: Call<List<Coin>>, response: Response<List<Coin>>) {
                Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful) {
                    coinList = response.body()?: listOf()
                    itemAdapter.setCoinList(coinList)
                }
            }

            override fun onFailure(call: Call<List<Coin>>, t: Throwable) {
                Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
}