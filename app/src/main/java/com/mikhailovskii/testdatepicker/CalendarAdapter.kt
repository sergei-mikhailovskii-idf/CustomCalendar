package com.mikhailovskii.testdatepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val items = mutableListOf<DayItem>()

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(data: DayItem) {

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