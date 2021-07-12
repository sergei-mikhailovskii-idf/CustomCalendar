package com.mikhailovskii.testdatepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val items = mutableListOf<DayItem>()
    private var selectedItem = -1

    inner class CalendarViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private lateinit var tvDate: AppCompatTextView
        private lateinit var tvMonth: AppCompatTextView
        private lateinit var tvDayDescription: AppCompatTextView
        private lateinit var clRoot: ConstraintLayout

        internal lateinit var onClickListener: () -> Unit

        fun bindData(data: DayItem, isSelected: Boolean) {
            tvDate = itemView.findViewById(R.id.tv_day_number)
            tvMonth = itemView.findViewById(R.id.tv_month)
            tvDayDescription = itemView.findViewById(R.id.tv_day_description)
            clRoot = itemView.findViewById(R.id.cl_root)

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

            clRoot.setOnClickListener {
                onClickListener.invoke()
            }

            clRoot.background = ContextCompat.getDrawable(
                itemView.context,
                when {
                    isSelected -> R.drawable.item_selected_background
                    data.isDateEnabled == true -> R.drawable.item_default_background
                    data.isToday() -> R.drawable.item_today_background
                    else -> R.drawable.item_disabled_background
                }
            )
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
        val item = items[position]
        holder.bindData(item, position == selectedItem)
        holder.onClickListener = {
            if (item.isDateEnabled == true && !item.isToday()) {
                selectedItem = holder.adapterPosition
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount() = items.size

    fun setItems(items: MutableList<DayItem>) {
        this.items.clear()
        this.items.addAll(items)
    }

}