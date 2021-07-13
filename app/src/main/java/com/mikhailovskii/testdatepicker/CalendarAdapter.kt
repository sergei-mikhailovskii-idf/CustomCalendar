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
    private var selectedDayOfYear: Int = -1

    abstract inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bindData(data: DayItem)
    }

    inner class CalendarEmptyViewHolder(view: View) : CalendarViewHolder(view) {
        override fun bindData(data: DayItem) {

        }
    }

    inner class CalendarWithDateViewHolder(view: View) :
        CalendarViewHolder(view) {

        private lateinit var tvDate: AppCompatTextView
        private lateinit var tvMonth: AppCompatTextView
        private lateinit var tvDayDescription: AppCompatTextView
        private lateinit var clRoot: ConstraintLayout

        internal lateinit var onClickListener: () -> Unit

        override fun bindData(data: DayItem) {
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
                    data.date?.get(Calendar.DAY_OF_YEAR) == selectedDayOfYear -> R.drawable.item_selected_background
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        CALENDAR_EMPTY_ITEM_TYPE -> onCreateCalendarEmptyViewHolder(parent)
        else -> onCreateCalendarWithDateViewHolder(parent)
    }

    override fun getItemViewType(position: Int) =
        if (items[position] is EmptyDayItem) CALENDAR_EMPTY_ITEM_TYPE else CALENDAR_WITH_DATE_ITEM_TYPE

    private fun onCreateCalendarWithDateViewHolder(parent: ViewGroup) = CalendarWithDateViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
    )

    private fun onCreateCalendarEmptyViewHolder(parent: ViewGroup) = CalendarEmptyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_empty, parent, false)
    )

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val item = items[position]
        holder.bindData(item)

        if (holder is CalendarWithDateViewHolder) {
            onBindCalendarWithDateViewHolder(holder, position, item)
        }
    }

    private fun onBindCalendarWithDateViewHolder(
        holder: CalendarWithDateViewHolder,
        position: Int,
        item: DayItem
    ) {
        holder.onClickListener = {
            if (item.isDateEnabled == true && !item.isToday()) {
                selectedDayOfYear = item.date?.get(Calendar.DAY_OF_YEAR) ?: 0
                notifyDataSetChanged()
            }
        }
    }

    private fun onBindCalendarEmptyViewHolder(
        holder: CalendarEmptyViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount() = items.size

    fun setItems(items: MutableList<DayItem>) {
        if (!items.isNullOrEmpty()) {
            val firstDayNumber = items[0].date?.get(Calendar.DAY_OF_WEEK) ?: 0
            for (i in 1 until firstDayNumber) {
                items.add(0, EmptyDayItem)
            }
            this.items.clear()
            this.items.addAll(items)
        }
    }

    companion object {
        private const val CALENDAR_EMPTY_ITEM_TYPE = 0
        private const val CALENDAR_WITH_DATE_ITEM_TYPE = 1
    }

}