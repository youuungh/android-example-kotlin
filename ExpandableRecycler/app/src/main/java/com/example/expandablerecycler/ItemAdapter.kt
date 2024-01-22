package com.example.expandablerecycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecycler.databinding.ItemListBinding

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val data = mapOf(
        "제목 1" to listOf("테스트입니다", "테스트입니다", "테스트입니다", "테스트입니다"),
        "제목 2" to listOf("테스트입니다", "테스트입니다", "테스트입니다"),
        "제목 3" to listOf("테스트입니다", "테스트입니다", "테스트입니다")
    )
    private val data2 = arrayListOf<String>()

    inner class ItemViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.button.setOnClickListener {
                if (binding.contentLayout.isVisible) {
                    binding.contentLayout.isVisible = false
                    binding.button.animate().apply {
                        duration = 200L
                        rotation(0f)
                    }
                } else {
                    binding.contentLayout.isVisible = true
                    binding.button.animate().apply {
                        duration = 200L
                        rotation(180f)
                    }
                }
            }
        }

        fun bind(position: Int) {
            binding.tvTitle.text = data.keys.elementAt(position)
            data.values.elementAt(position).forEach {
                val view = TextView(binding.root.context).apply {
                    text = "$it"
                    textSize = 20f
                    setPadding(10, 10, 5, 10)
                }

                val intent = Intent(binding.root.context, ContentActivity::class.java)
                    .putExtra("title", view.text.toString())

                view.setOnClickListener {
                    binding.root.context.startActivity(intent)
                }

                binding.contentLayout.addView(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}