package com.mikhailovskii.testdatepicker.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import com.mikhailovskii.testdatepicker.R
import com.mikhailovskii.testdatepicker.base.DayItem
import com.mikhailovskii.testdatepicker.base.ItemStrategy
import java.util.*

class CalendarNewMonthDateViewHolder(view: View) : CalendarDateViewHolder(view),
    ItemStrategy.EnabledItemStrategy<DayItem> {

    override lateinit var onClickListener: () -> Unit

    override fun bindData(data: DayItem, selectedDay: Int) {
        tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()
        val monthName = itemView.resources
            .getStringArray(R.array.calendar_month_names)[data.date?.get(Calendar.MONTH) ?: 0]
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
