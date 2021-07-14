package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.core.content.ContextCompat
import java.util.*

class CalendarWithDateViewHolder(view: View) : CalendarDateViewHolder(view),
    ItemStrategy.EnabledItemStrategy<DayItem> {

    override lateinit var onClickListener: () -> Unit

    override fun bindData(data: DayItem, selectedItemPosition: Int) {
        tvDate.text = data.date?.get(Calendar.DAY_OF_MONTH)?.toString()

        clRoot.setOnClickListener {
            onClickListener.invoke()
        }

        clRoot.background = ContextCompat.getDrawable(
            itemView.context,
            when (selectedItemPosition) {
                data.date?.get(Calendar.DAY_OF_YEAR) -> R.drawable.item_selected_background
                else -> R.drawable.item_default_background
            }
        )
        tvDate.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                if (selectedItemPosition == data.date?.get(Calendar.DAY_OF_YEAR)) R.color.white
                else R.color.black
            )
        )
    }
}