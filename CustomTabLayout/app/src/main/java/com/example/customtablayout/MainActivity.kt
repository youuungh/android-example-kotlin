package com.example.customtablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.customtablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var tabTextList = listOf("Home", "List", "Profile")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout1, binding.viewPager) { tab, pos ->
            tab.text = tabTextList[pos]
        }.attach()
        TabLayoutMediator(binding.tabLayout2, binding.viewPager) { tab, pos ->
            tab.text = tabTextList[pos]
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            var currentState = 0
            var currentPos = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (currentState == ViewPager2.SCROLL_STATE_DRAGGING && currentPos == position) {
                    if (currentPos == 0) binding.viewPager.currentItem = 2
                    else if (currentPos == 2) binding.viewPager.currentItem = 0
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPos = position
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state
                super.onPageScrollStateChanged(state)
            }
        })

        setTabItemMargin(binding.tabLayout1, 30)
        setTabItemMargin(binding.tabLayout2, 30)
    }

    private fun setTabItemMargin(tabLayout: TabLayout, marginEnd: Int) {
        for(i in tabTextList.indices) {
            val tabs = tabLayout.getChildAt(0) as ViewGroup
            for(i in 0 until tabs.childCount) {
                val tab = tabs.getChildAt(i)
                val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
                layoutParams.marginEnd = marginEnd
                tab.layoutParams = layoutParams
                tabLayout.requestLayout()
            }
        }
    }
}