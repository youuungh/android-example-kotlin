package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.databinding.ItemList1Binding

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val data = mapOf(
        "제목1" to listOf("1", "2", "3", "4", "5"),
        "제목2" to listOf("1", "2", "3", "4", "5"),
        "제목3" to listOf("1", "2", "3", "4", "5"),
        "제목4" to listOf("1", "2", "3", "4", "5")
    )

    inner class ItemViewHolder(private val binding: ItemList1Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvTitle.text = data.keys.elementAt(position)
            binding.recycler2.apply {
                layoutManager = LinearLayoutManager(binding.recycler2.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ItemAdapter2(data.values.elementAt(position), position)
                setHasFixedSize(true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val binding = ItemList1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}