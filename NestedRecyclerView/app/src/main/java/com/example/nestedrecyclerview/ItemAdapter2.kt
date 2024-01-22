package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ItemList2Binding

class ItemAdapter2(
    private val data: List<String>,
    private val parentPosition: Int
): RecyclerView.Adapter<ItemAdapter2.ItemViewHolder>() {
    val imageList = listOf(R.drawable.cyberpunk1, R.drawable.cyberpunk2, R.drawable.cyberpunk3, R.drawable.cyberpunk4)

    inner class ItemViewHolder(private val binding: ItemList2Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.image.setImageResource(imageList[parentPosition])
            binding.tvContentTitle.text = data[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemList2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}