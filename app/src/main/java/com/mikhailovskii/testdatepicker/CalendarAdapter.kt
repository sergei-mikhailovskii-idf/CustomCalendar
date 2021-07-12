package com.mikhailovskii.testdatepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val items = mutableListOf<DayItem>()

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var tvDate: AppCompatTextView
        private lateinit var tvMonth: AppCompatTextView
        private lateinit var tvDayDescription: AppCompatTextView

        fun bindData(data: DayItem) {
            tvDate = itemView.findViewById(R.id.tv_day_number)
            tvMonth = itemView.findViewById(R.id.tv_month)
            tvDayDescription = itemView.findViewById(R.id.tv_day_description)

            tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()
            if (data.isNewMonth()) {
                showNewMonthData(data)
            } else {
                tvMonth.text = ""
            }

            tvDayDescription.text = if (data.isToday()) {
                "hoy"
            } else {
                ""
            }
        }

        private fun showNewMonthData(data: DayItem) {
            val monthName = when (data.date?.get(Calendar.MONTH)) {
                0 -> "January"
                1 -> "February"
                else -> "March"
            }
            tvMonth.text = monthName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CalendarViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
    )

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(items: MutableList<DayItem>) {
        this.items.clear()
        this.items.addAll(items)
    }

}