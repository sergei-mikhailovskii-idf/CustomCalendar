package com.mikhailovskii.testdatepicker.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import com.mikhailovskii.testdatepicker.DayItem
import com.mikhailovskii.testdatepicker.base.ItemStrategy
import com.mikhailovskii.testdatepicker.R
import it.sephiroth.android.library.xtooltip.ClosePolicy
import it.sephiroth.android.library.xtooltip.Tooltip
import java.util.*

class CalendarNewMonthDisabledViewHolder(view: View) : CalendarDateViewHolder(view),
    ItemStrategy.DisabledItemStrategy<DayItem> {

    override lateinit var onClickListener: () -> Unit

    override fun bindData(data: DayItem, selectedDayOfYear: Int) {
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
        clRoot.background = ContextCompat.getDrawable(
            itemView.context,
            R.drawable.item_disabled_background
        )
        if (data.date?.get(Calendar.DAY_OF_YEAR) == selectedDayOfYear) {
            var tooltip: Tooltip? = Tooltip.Builder(itemView.context)
                .anchor(clRoot, 0, 0, false)
                .text("El plazo mínimo de tu EXTENSIÓN debe ser por 4 días")
                .maxWidth(itemView.context.resources.displayMetrics.widthPixels * 2 / 3)
                .arrow(true)
                .closePolicy(ClosePolicy.TOUCH_ANYWHERE_CONSUME)
                .styleId(R.style.ToolTipAltStyle)
                .overlay(false)
                .create()
            clRoot.post {
                tooltip
                    ?.doOnHidden { tooltip = null }
                    ?.show(clRoot, Tooltip.Gravity.TOP, true)
            }
        }

    }
}