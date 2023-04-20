package com.example.music.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.music.R
import com.example.music.adapter.PagerAdapter
import com.example.music.fragment.HomeFragment
import com.example.music.fragment.SearchFragment
import com.google.android.material.tabs.TabLayout
import android.view.View.VISIBLE
import android.view.View.GONE
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        create()
        progressBar.visibility = View.GONE


    }

    private fun create() {
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
//        val fragments = listOf(HomeFragment(), SearchFragment())
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment("Trang chủ", HomeFragment())
        adapter.addFragment("Tìm Kiếm", SearchFragment())
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_search)
    }
}