package com.example.music.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = mutableListOf<Fragment>()
    private val arrTitle = mutableListOf<String>()
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrTitle[position]
    }
    fun addFragment(title: String, fragment: Fragment) {
        arrTitle.add(title)
        fragments.add(fragment)
        notifyDataSetChanged()
    }
}