package com.example.customcalendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.customcalendar.databinding.DayItemListBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class DayAdapter(private val tempMonth: Int, private val dayList: MutableList<LocalDate>):
    RecyclerView.Adapter<DayAdapter.DayViewHolder>() {
    private val row = 6

    inner class DayViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val contentBinding = DayItemListBinding.bind(itemView)
        var tvDay: TextView = contentBinding.tvDay
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.day_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${dayList[position]}", Toast.LENGTH_SHORT).show()
        }

//        holder.tvDay.text = dayList[position].date.toString()
//        holder.tvDay.setTextColor(when(position % 7) {
//            0 -> Color.RED
//            6 -> Color.BLUE
//            else -> Color.BLACK
//        })
//
//        if (tempMonth != dayList[position].month) {
//            holder.tvDay.alpha = 0.4f
//        }

        val dateFormatter = DateTimeFormatter.ofPattern("dd")
        holder.tvDay.text = dateFormatter.format(dayList[position])
        holder.tvDay.setTextColor(
            when(position % 7) {
            0 -> Color.RED
            6 -> Color.BLUE
            else -> Color.BLACK
        })

        if (tempMonth != dayList[position].month.value) {
            holder.tvDay.alpha = 0.4f
        }
    }

    override fun getItemCount(): Int {
        return row * 7
    }
}