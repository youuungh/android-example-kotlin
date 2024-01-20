package com.example.customtablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ScreenFragment1()
            2 -> ScreenFragment2()
            else -> ScreenFragment3()
        }
    }

    override fun getItemCount(): Int = 3
}