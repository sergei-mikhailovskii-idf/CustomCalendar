package com.mikhailovskii.testdatepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarAdapter : RecyclerView.Adapter<CalendarViewHolder<CalendarItem>>() {

    private val items = mutableListOf<CalendarItem>()
    private var selectedDayOfYear = -1
    private var previousClickedPosition = -1

    inner class CalendarNewMonthDateViewHolder(view: View) : CalendarDateViewHolder(view),
        OnClickStrategy {

        override lateinit var onClickListener: () -> Unit

        override fun bindData(data: DayItem) {
            super.bindData(data)
            tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()
            val monthName = when (data.date?.get(Calendar.MONTH)) {
                0 -> "January"
                1 -> "February"
                else -> "March"
            }
            tvMonth.text = monthName
            clRoot.setOnClickListener {
                onClickListener.invoke()
            }
            tvDate.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    if (selectedDayOfYear == data.date?.get(Calendar.DAY_OF_YEAR)) R.color.white
                    else R.color.black
                )
            )
            clRoot.background = ContextCompat.getDrawable(
                itemView.context,
                when (selectedDayOfYear) {
                    data.date?.get(Calendar.DAY_OF_YEAR) -> R.drawable.item_selected_background
                    else -> R.drawable.item_default_background
                }
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarViewHolder<CalendarItem> = when (viewType) {
        CALENDAR_EMPTY_ITEM_TYPE -> onCreateCalendarEmptyViewHolder(parent)
        CALENDAR_TODAY_DATE_ITEM_TYPE -> onCreateCalendarTodayDateViewHolder(parent)
        CALENDAR_NEW_MONTH_DATE_ITEM_TYPE -> onCreateCalendarNewMonthDateViewHolder(parent)
        CALENDAR_WITH_DATE_ITEM_TYPE -> onCreateCalendarWithDateViewHolder(parent)
        CALENDAR_HEADER_ITEM_TYPE -> onCreateCalendarHeaderViewHolder(parent)
        else -> onCreateCalendarDisabledDateViewHolder(parent)
    }

    override fun getItemViewType(position: Int) = when (val item = items[position]) {
        EmptyDayItem -> CALENDAR_EMPTY_ITEM_TYPE
        is DayItem -> when {
            item.isToday() -> CALENDAR_TODAY_DATE_ITEM_TYPE
            item.isNewMonth() -> CALENDAR_NEW_MONTH_DATE_ITEM_TYPE
            item.isDateEnabled -> CALENDAR_WITH_DATE_ITEM_TYPE
            else -> CALENDAR_DISABLED_DATE_ITEM_TYPE
        }
        is HeaderDayNameItem -> CALENDAR_HEADER_ITEM_TYPE
        else -> UNKNOWN_ITEM_TYPE
    }

    private fun onCreateCalendarWithDateViewHolder(parent: ViewGroup) = CalendarWithDateViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
    ) as CalendarViewHolder<CalendarItem>

    private fun onCreateCalendarEmptyViewHolder(parent: ViewGroup) = CalendarEmptyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_empty, parent, false)
    ) as CalendarViewHolder<CalendarItem>

    private fun onCreateCalendarTodayDateViewHolder(parent: ViewGroup) =
        CalendarTodayDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        ) as CalendarViewHolder<CalendarItem>

    private fun onCreateCalendarNewMonthDateViewHolder(parent: ViewGroup) =
        CalendarNewMonthDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        ) as CalendarViewHolder<CalendarItem>

    private fun onCreateCalendarDisabledDateViewHolder(parent: ViewGroup) =
        CalendarDisabledDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        ) as CalendarViewHolder<CalendarItem>

    private fun onCreateCalendarHeaderViewHolder(parent: ViewGroup) =
        CalendarHeaderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_calendar_header, parent, false)
        ) as CalendarViewHolder<CalendarItem>

    override fun onBindViewHolder(holder: CalendarViewHolder<CalendarItem>, position: Int) {
        val item = items[position]
        holder.bindData(item)

        if (item is DayItem) {
            if (holder is OnClickStrategy) {
                holder.onClickListener = {
                    selectedDayOfYear = item.date?.get(Calendar.DAY_OF_YEAR) ?: 0
                    notifyItemChanged(position)
                    if (previousClickedPosition != -1) {
                        notifyItemChanged(previousClickedPosition)
                    }
                    previousClickedPosition = holder.adapterPosition
                }
            }
        }
    }

    override fun getItemCount() = items.size

    fun setItems(items: MutableList<DayItem>) {
        val localItems: MutableList<CalendarItem> = items.toMutableList()
        if (!items.isNullOrEmpty()) {
            val firstDayNumber = items[0].date?.get(Calendar.DAY_OF_WEEK) ?: 0
            for (i in 1 until firstDayNumber) {
                localItems.add(0, EmptyDayItem)
            }
            for (i in 0 until 7) {
                localItems.add(0, HeaderDayNameItem("DO"))
            }
            this.items.clear()
            this.items.addAll(localItems)
        }
    }

    companion object {
        private const val CALENDAR_EMPTY_ITEM_TYPE = 0
        private const val CALENDAR_TODAY_DATE_ITEM_TYPE = 1
        private const val CALENDAR_NEW_MONTH_DATE_ITEM_TYPE = 2
        private const val CALENDAR_WITH_DATE_ITEM_TYPE = 3
        private const val CALENDAR_DISABLED_DATE_ITEM_TYPE = 4
        private const val CALENDAR_HEADER_ITEM_TYPE = 5
        private const val UNKNOWN_ITEM_TYPE = -1
    }

}