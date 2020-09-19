package com.hodinv.products.screens.types.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hodinv.products.R
import com.hodinv.products.model.ListItem

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:visible")
    fun setVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = ["app:adapter", "app:items"], requireAll = true)
    fun setupList(recyclerView: RecyclerView, adapter: ItemsAdapter, list: List<ListItem>) {
        if (recyclerView.adapter != adapter) {
            recyclerView.adapter = adapter
        }
        adapter.items = list
    }


    @JvmStatic
    @BindingAdapter("app:image")
    fun setImage(imageView: ImageView, url: String) {
        if (url.isNotBlank()) {
            Glide.with(imageView).load(imageView.context.getString(R.string.base_url) + url)
                .error(R.drawable.ic_block)
                .into(imageView)
        }
    }
}