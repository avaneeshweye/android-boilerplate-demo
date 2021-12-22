package com.boilerplate.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.boilerplate.demo.base.ui.BaseFragment
import com.boilerplate.demo.databinding.FragmentDetailLayoutBinding
import com.boilerplate.demo.databinding.FragmentTabBinding
import com.boilerplate.demo.viewmodel.AppViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabFragment : BaseFragment() {

    private val viewModel: AppViewModel by activityViewModels()
    lateinit var binding: FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter = context?.let { SectionsPagerAdapter(it, childFragmentManager) }
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }


    companion object {

        @JvmStatic
        fun newInstance(): TabFragment {
            return TabFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
}