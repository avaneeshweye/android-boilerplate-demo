package com.boilerplate.demo.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.boilerplate.demo.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return ItemListFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }


    override fun getCount(): Int {
        return 2
    }
}