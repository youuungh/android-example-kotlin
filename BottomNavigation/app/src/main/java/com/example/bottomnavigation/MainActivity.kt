package com.example.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bottomnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navBottomLayout, NavHomeFragment())
            .commit()

        binding.navBottom.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navHome -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.navBottomLayout, NavHomeFragment())
                    .commit()
                R.id.navList -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.navBottomLayout, NavListFragment())
                    .commit()
                R.id.navProfile -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.navBottomLayout, NavProfileFragment())
                    .commit()
            }
            true
        }
    }
}