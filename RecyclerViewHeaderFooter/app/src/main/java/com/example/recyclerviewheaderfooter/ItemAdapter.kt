package com.example.recyclerviewheaderfooter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewheaderfooter.databinding.ItemFooterBinding
import com.example.recyclerviewheaderfooter.databinding.ItemHeaderBinding
import com.example.recyclerviewheaderfooter.databinding.ItemListBinding

class ItemAdapter(private var itemList: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HEADER = 0
        private const val ITEM = 1
        private const val FOOTER = 2
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderBinding): RecyclerView.ViewHolder(binding.root) {}

    inner class FooterViewHolder(private val binding: ItemFooterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.tvAdd.setOnClickListener {
                itemList.add("")
                this@ItemAdapter.notifyDataSetChanged()
            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.checkBox.text = "제목 $position"
            binding.tvContent.text = "내용 $position"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> HeaderViewHolder(
                ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            FOOTER -> FooterViewHolder(
                ItemFooterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ItemViewHolder(
                ItemListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return itemList.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER
        } else if (position == itemList.size + 1) {
            FOOTER
        } else {
            ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {}
            is FooterViewHolder -> {
                holder.bind()
            }
            is ItemViewHolder -> {
                holder.bind(position)
            }
        }
    }

}