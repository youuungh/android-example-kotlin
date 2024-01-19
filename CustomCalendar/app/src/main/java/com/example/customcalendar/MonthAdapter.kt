package com.example.customcalendar

import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customcalendar.databinding.MonthItemListBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class MonthAdapter: RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {
    private var calendar = Calendar.getInstance()
    private val center = Int.MAX_VALUE / 2

    inner class MonthViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val contentBinding = MonthItemListBinding.bind(itemView)
        var title: TextView = contentBinding.tvItemMonth
        var rvMonth: RecyclerView = contentBinding.rvMonth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.month_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
//        calendar.time = Date()
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        calendar.add(Calendar.MONTH, position - center)
//
//        holder.title.text = "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH)+1}월"
//
//        var tempMonth = calendar.get(Calendar.MONTH)
//        var dayList: MutableList<Date> = MutableList(6 * 7) { Date() }
//        for (i in 0..5) {
//            for (k in 0..6) {
//                calendar.add(Calendar.DAY_OF_MONTH, (1 - calendar.get(Calendar.DAY_OF_WEEK)) + k)
//                dayList[i * 7 + k] = calendar.time
//            }
//            calendar.add(Calendar.WEEK_OF_MONTH, 1)
//        }

        val now = LocalDate.now()
        var baseDate = now.withDayOfMonth(1).plusMonths((position - center).toLong())
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")
        holder.title.text = formatter.format(baseDate)

        val tempMonth = baseDate.monthValue
        val dayList: MutableList<LocalDate> = MutableList(6 * 7) { LocalDate.MIN }
        for (i in 0..5) {
            for (k in 0..6) {
                dayList[i * 7 + k] = baseDate.withDayOfMonth(1).plusDays(((1 - baseDate.dayOfWeek.value) + k).toLong())
            }
            baseDate.plusWeeks(1)
        }

        holder.rvMonth.apply {
            layoutManager = GridLayoutManager(holder.itemView.context, 7)
            adapter = DayAdapter(tempMonth, dayList)
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}