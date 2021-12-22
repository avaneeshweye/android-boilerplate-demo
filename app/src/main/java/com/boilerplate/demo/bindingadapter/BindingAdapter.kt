package com.boilerplate.demo.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.boilerplate.demo.R
import com.bumptech.glide.Glide


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("data")
    fun <T> loadRecyclerData(recyclerView: RecyclerView, data: T?) {
        if (recyclerView.adapter is BindableAdapter<*>) {
            data?.let { (recyclerView.adapter as BindableAdapter<T>).setData(it) }
        }
    }



    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, image: String?) {

        if (image?.contains("http", false)==true) {
            Glide.with(view.context)
                .load(image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)
            return
        }

    }
}