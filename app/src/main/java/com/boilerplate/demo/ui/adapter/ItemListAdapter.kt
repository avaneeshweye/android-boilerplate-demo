package com.boilerplate.demo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boilerplate.demo.bindingadapter.BindableAdapter
import com.boilerplate.demo.databinding.ListItemLayoutBinding
import com.boilerplate.demo.model.ItemResponse
import com.boilerplate.demo.viewmodel.AppViewModel

class ItemListAdapter(
    private val viewModel: AppViewModel
) :
    RecyclerView.Adapter<ListItemHolder>(), BindableAdapter<List<ItemResponse>> {

    private var data: MutableList<ItemResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val binding =
            ListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context!!),
                parent,
                false
            )
        binding.viewModel = viewModel
        return ListItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        holder.onBind(data.get(position))
    }

    override fun setData(data: List<ItemResponse>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}
