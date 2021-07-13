package com.mikhailovskii.testdatepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val items = mutableListOf<DayItem>()
    private var selectedDayOfYear: Int = -1

    abstract inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bindData(data: DayItem) {}
    }

    abstract inner class CalendarDateViewHolder(view: View) : CalendarViewHolder(view) {

        protected lateinit var tvDate: AppCompatTextView
        protected lateinit var tvMonth: AppCompatTextView
        protected lateinit var tvDayDescription: AppCompatTextView
        protected lateinit var clRoot: ConstraintLayout

        @CallSuper
        override fun bindData(data: DayItem) {
            tvDate = itemView.findViewById(R.id.tv_day_number)
            tvMonth = itemView.findViewById(R.id.tv_month)
            tvDayDescription = itemView.findViewById(R.id.tv_day_description)
            clRoot = itemView.findViewById(R.id.cl_root)
        }
    }

    interface OnClickStrategy {
        var onClickListener: () -> Unit
    }

    inner class CalendarEmptyViewHolder(view: View) : CalendarViewHolder(view)

    inner class CalendarWithDateViewHolder(view: View) :
        CalendarDateViewHolder(view), OnClickStrategy {

        override lateinit var onClickListener: () -> Unit

        override fun bindData(data: DayItem) {
            super.bindData(data)

            tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()

            clRoot.setOnClickListener {
                onClickListener.invoke()
            }

            clRoot.background = ContextCompat.getDrawable(
                itemView.context,
                when (selectedDayOfYear) {
                    data.date?.get(Calendar.DAY_OF_YEAR) -> R.drawable.item_selected_background
                    else -> R.drawable.item_default_background
                }
            )
            tvDate.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    if (selectedDayOfYear == data.date?.get(Calendar.DAY_OF_YEAR)) R.color.white
                    else R.color.black
                )
            )
        }
    }

    inner class CalendarTodayDateViewHolder(view: View) : CalendarDateViewHolder(view) {
        override fun bindData(data: DayItem) {
            super.bindData(data)
            tvDayDescription.text = "hoy"
            tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()
            clRoot.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.item_today_background
            )
            val monthName = when (data.date?.get(Calendar.MONTH)) {
                0 -> "January"
                1 -> "February"
                else -> "March"
            }
            tvMonth.text = monthName
            tvDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            tvDayDescription.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            tvMonth.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
        }
    }

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

    inner class CalendarDisabledDateViewHolder(view: View) : CalendarDateViewHolder(view) {

        override fun bindData(data: DayItem) {
            super.bindData(data)
            tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()
            clRoot.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.item_disabled_background
            )
            tvDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.silver))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        CALENDAR_EMPTY_ITEM_TYPE -> onCreateCalendarEmptyViewHolder(parent)
        CALENDAR_TODAY_DATE_ITEM_TYPE -> onCreateCalendarTodayDateViewHolder(parent)
        CALENDAR_NEW_MONTH_DATE_ITEM_TYPE -> onCreateCalendarNewMonthDateViewHolder(parent)
        CALENDAR_WITH_DATE_ITEM_TYPE -> onCreateCalendarWithDateViewHolder(parent)
        else -> onCreateCalendarDisabledDateViewHolder(parent)
    }

    override fun getItemViewType(position: Int) = when {
        items[position] is EmptyDayItem -> CALENDAR_EMPTY_ITEM_TYPE
        items[position].isToday() -> CALENDAR_TODAY_DATE_ITEM_TYPE
        items[position].isNewMonth() -> CALENDAR_NEW_MONTH_DATE_ITEM_TYPE
        items[position].isDateEnabled -> CALENDAR_WITH_DATE_ITEM_TYPE
        else -> CALENDAR_DISABLED_DATE_ITEM_TYPE
    }

    private fun onCreateCalendarWithDateViewHolder(parent: ViewGroup) = CalendarWithDateViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
    )

    private fun onCreateCalendarEmptyViewHolder(parent: ViewGroup) = CalendarEmptyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_empty, parent, false)
    )

    private fun onCreateCalendarTodayDateViewHolder(parent: ViewGroup) =
        CalendarTodayDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        )

    private fun onCreateCalendarNewMonthDateViewHolder(parent: ViewGroup) =
        CalendarNewMonthDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        )

    private fun onCreateCalendarDisabledDateViewHolder(parent: ViewGroup) =
        CalendarDisabledDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        )

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val item = items[position]
        holder.bindData(item)

        if (holder is OnClickStrategy) {
            holder.onClickListener = {
                selectedDayOfYear = item.date?.get(Calendar.DAY_OF_YEAR) ?: 0
                notifyDataSetChanged()
            }
        }
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
        private const val CALENDAR_TODAY_DATE_ITEM_TYPE = 1
        private const val CALENDAR_NEW_MONTH_DATE_ITEM_TYPE = 2
        private const val CALENDAR_WITH_DATE_ITEM_TYPE = 3
        private const val CALENDAR_DISABLED_DATE_ITEM_TYPE = 4
    }

}