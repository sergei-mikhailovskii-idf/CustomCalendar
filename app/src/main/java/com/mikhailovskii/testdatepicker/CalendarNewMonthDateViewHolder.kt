package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.core.content.ContextCompat
import java.util.*

class CalendarNewMonthDateViewHolder(view: View) : CalendarDateViewHolder(view),
    ItemStrategy.EnabledItemStrategy<DayItem> {

    override lateinit var onClickListener: () -> Unit

    override fun bindData(data: DayItem, selectedDay: Int) {
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
                if (selectedDay == data.date?.get(Calendar.DAY_OF_YEAR)) R.color.white
                else R.color.black
            )
        )
        clRoot.background = ContextCompat.getDrawable(
            itemView.context,
            when (selectedDay) {
                data.date?.get(Calendar.DAY_OF_YEAR) -> R.drawable.item_selected_background
                else -> R.drawable.item_default_background
            }
        )
    }
}
