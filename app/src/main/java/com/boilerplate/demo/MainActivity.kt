package com.boilerplate.demo

import android.os.Bundle
import androidx.activity.viewModels
import com.boilerplate.demo.base.ui.BaseActivity
import com.boilerplate.demo.databinding.ActivityMainBinding
import com.boilerplate.demo.ui.main.DetailFragment
import com.boilerplate.demo.ui.main.TabFragment
import com.boilerplate.demo.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, TabFragment.newInstance()).commit()

        if (viewModel.list.value?.data == null) {
            viewModel.getItemList()
        }

        viewModel.itemResponseClick.observe(this) {
            val indexOf = viewModel.list.value?.data?.indexOf(it)
            indexOf?.let { index ->
                supportFragmentManager.beginTransaction()
                    .add(binding.container.id, DetailFragment.newInstance(index))
                    .addToBackStack("detail").commit()
            }


        }
    }


}