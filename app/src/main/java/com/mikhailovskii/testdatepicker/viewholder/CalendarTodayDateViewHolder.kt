package com.mikhailovskii.testdatepicker.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import com.mikhailovskii.testdatepicker.DayItem
import com.mikhailovskii.testdatepicker.base.ItemStrategy
import com.mikhailovskii.testdatepicker.R
import java.util.*

class CalendarTodayDateViewHolder(view: View) : CalendarDateViewHolder(view),
    ItemStrategy.NoClickItemStrategy<DayItem> {

    override fun bindData(data: DayItem) {
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