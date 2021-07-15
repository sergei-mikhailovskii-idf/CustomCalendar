package com.mikhailovskii.testdatepicker.utils

import com.mikhailovskii.testdatepicker.base.DayItem
import java.util.*

object CalendarUtils {

    private const val FIRST_DISABLED_DAYS_AMOUNT = 4

    fun generateDaysBetweenTwoDates(begin: Calendar, end: Calendar): MutableList<DayItem> {
        val dayItems = mutableListOf<DayItem>()
        while (begin.time < end.time) {
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
}