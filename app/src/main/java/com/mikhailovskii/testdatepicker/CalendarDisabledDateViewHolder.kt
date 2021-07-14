package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.core.content.ContextCompat
import java.util.*

class CalendarDisabledDateViewHolder(view: View) : CalendarDateViewHolder(view) {

    override fun bindData(data: DayItem, selectedDayOfYear: Int) {
        super.bindData(data, selectedDayOfYear)
        tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()
        clRoot.background = ContextCompat.getDrawable(
            itemView.context,
            R.drawable.item_disabled_background
        )
        tvDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.silver))
    }
}