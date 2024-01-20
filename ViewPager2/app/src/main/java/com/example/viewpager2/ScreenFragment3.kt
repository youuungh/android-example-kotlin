package com.example.viewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewpager2.databinding.FragmentScreen3Binding

class ScreenFragment3 : Fragment() {
    private lateinit var binding: FragmentScreen3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreen3Binding.inflate(inflater, container, false)
        return binding.root
    }
}