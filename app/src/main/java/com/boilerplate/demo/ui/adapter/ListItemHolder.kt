package com.boilerplate.demo.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.boilerplate.demo.databinding.ListItemLayoutBinding
import com.boilerplate.demo.model.ItemResponse

class ListItemHolder(private val binding: ListItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(response: ItemResponse) {
        binding.itemResponse = response
        binding.executePendingBindings()
    }

}
