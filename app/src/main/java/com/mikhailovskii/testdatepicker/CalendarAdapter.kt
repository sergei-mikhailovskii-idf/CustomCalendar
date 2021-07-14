package com.mikhailovskii.testdatepicker

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarAdapter : RecyclerView.Adapter<CalendarViewHolder<CalendarItem>>() {

    private val items = mutableListOf<CalendarItem>()
    private var previousClickedPosition = -1
    private var selectedDayOfYear = -1

    private val onCreateViewHolder = OnCreateViewHolderFacade()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = onCreateViewHolder.onCreateViewHolder(parent, viewType)

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

    override fun onBindViewHolder(holder: CalendarViewHolder<CalendarItem>, position: Int) {
        val item = items[position]
        holder.bindData(item, selectedDayOfYear)

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

    fun setItems(items: MutableList<DayItem>, headers: MutableList<String>) {
        val localItems: MutableList<CalendarItem> = mutableListOf()
        headers.forEach {
            localItems.add(HeaderDayNameItem(it))
        }
        if (!items.isNullOrEmpty()) {
            val firstDayNumber = items[0].date?.get(Calendar.DAY_OF_WEEK) ?: 0
            for (i in 1 until firstDayNumber) {
                localItems.add(EmptyDayItem)
            }
            localItems.addAll(items)
            this.items.clear()
            this.items.addAll(localItems)
        }
    }

    companion object {
        const val CALENDAR_EMPTY_ITEM_TYPE = 0
        const val CALENDAR_TODAY_DATE_ITEM_TYPE = 1
        const val CALENDAR_NEW_MONTH_DATE_ITEM_TYPE = 2
        const val CALENDAR_WITH_DATE_ITEM_TYPE = 3
        const val CALENDAR_DISABLED_DATE_ITEM_TYPE = 4
        const val CALENDAR_HEADER_ITEM_TYPE = 5
        const val UNKNOWN_ITEM_TYPE = -1
    }

}