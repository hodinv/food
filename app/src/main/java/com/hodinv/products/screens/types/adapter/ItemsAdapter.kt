package com.hodinv.products.screens.types.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hodinv.products.R
import com.hodinv.products.databinding.ListitemBinding
import com.hodinv.products.model.ListItem

class ItemsAdapter(private val callback: OnItemClicked) : RecyclerView.Adapter<ItemViewHolder>() {
    var items: List<ListItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ListitemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.listitem,
            parent, false
        )
        binding.callback = callback
        return ItemViewHolder(binding, binding.root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

}