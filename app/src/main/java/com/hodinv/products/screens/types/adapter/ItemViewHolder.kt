package com.hodinv.products.screens.types.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hodinv.products.databinding.ListitemBinding
import com.hodinv.products.model.ListItem

class ItemViewHolder(private val binding: ListitemBinding, view: View) :
    RecyclerView.ViewHolder(view) {
    fun bind(item: ListItem) {
        binding.let {
            it.item = item
            it.executePendingBindings()
        }
    }
}