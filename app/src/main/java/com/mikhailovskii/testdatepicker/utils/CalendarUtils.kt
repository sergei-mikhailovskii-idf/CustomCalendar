package com.mikhailovskii.testdatepicker.utils

import com.mikhailovskii.testdatepicker.base.DayItem
import java.util.*
import java.util.concurrent.TimeUnit

object CalendarUtils {

    private const val FIRST_DISABLED_DAYS_AMOUNT = 4

    fun generateDaysBetweenTwoDates(begin: Calendar, end: Calendar): MutableList<DayItem> {
        val dayItems = mutableListOf<DayItem>()
        while (begin.get(Calendar.DAY_OF_YEAR) < end.get(Calendar.DAY_OF_YEAR)) {
            dayItems.add(DayItem(date = Calendar.getInstance().apply {
                time = begin.time
            }))
            begin.add(Calendar.DAY_OF_MONTH, 1)
        }
        if (dayItems.size > FIRST_DISABLED_DAYS_AMOUNT) {
            for (i in 1 until FIRST_DISABLED_DAYS_AMOUNT) {
                dayItems[i].isDateEnabled = false
            }
        }
        return dayItems
    }

    fun calculateDiff(from: Calendar?, to: Calendar?) : Long? {
        if (from == null || to == null) return null
        val startTime = from.time.time
        val endTime = to.time.time
        val diff = endTime - startTime
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    }

    fun formatDate(date: Calendar) =
        "${date.get(Calendar.DAY_OF_MONTH)}-${date.get(Calendar.MONTH)}-${date.get(Calendar.YEAR)}"
}