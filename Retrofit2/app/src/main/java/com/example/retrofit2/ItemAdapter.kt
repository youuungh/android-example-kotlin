package com.example.retrofit2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2.databinding.ItemListBinding

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var coinList = listOf<Coin>()

    fun setCoinList(list: List<Coin>) {
        this.coinList = list
    }

    inner class ItemViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvText1.text = coinList[position].market
            binding.tvText2.text = coinList[position].korean_name
            binding.tvText3.text = coinList[position].english_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }
}