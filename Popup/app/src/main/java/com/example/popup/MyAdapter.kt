package com.example.popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.popup.databinding.ListItemLayoutBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.ListViewHolder>() {
    private val titleList = listOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5")
    private val contentList = listOf("Content 1", "Content 2", "Content 3", "Content 4", "Content 5")
    private var selectPos = -1

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val contentBinding = ListItemLayoutBinding.bind(itemView)
        val title: TextView = contentBinding.tvTitle
        val content: TextView = contentBinding.tvContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if (selectPos == holder.adapterPosition) {
            holder.itemView.setBackgroundColor(Color.parseColor("#e8e8e8"))
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

        holder.title.text = titleList[position]
        holder.content.text = contentList[position]

        holder.itemView.setOnClickListener {
            var beforePos = selectPos
            selectPos = holder.adapterPosition

            val popup = PopupMenu(holder.itemView.context, it)
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
            popup.setOnMenuItemClickListener {
                var text = when(it.itemId) {
                    R.id.mItem1 -> "${position+1}번 편집"
                    else -> "${position+1}번 클릭"
                }
                Toast.makeText(holder.itemView.context, text, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()

            notifyItemChanged(beforePos)
            notifyItemChanged(selectPos)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return titleList.size
    }
}