package com.example.swiperefresh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swiperefresh.databinding.ItemListBinding

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var count = 3

    inner class ItemViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pos: Int) {
            binding.tvTitle.text = "제목 ${pos+1}"
            binding.tvContent.text = "내용 ${pos+1}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return count
    }

    fun addItem() {
        this.count++
    }

    fun removeItem() {
        if (this.count > 0) this.count--
    }
}