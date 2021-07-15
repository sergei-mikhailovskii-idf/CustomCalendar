package com.mikhailovskii.testdatepicker.utils

import com.mikhailovskii.testdatepicker.DayItem
import java.util.*

object CalendarUtils {
    fun generateDaysBetweenTwoDates(begin: Calendar, end: Calendar): MutableList<DayItem> {
        val dayItems = mutableListOf<DayItem>()
        while (begin.time < end.time) {
            dayItems.add(DayItem(date = Calendar.getInstance().apply {
                time = begin.time
            }))
            begin.add(Calendar.DAY_OF_MONTH, 1)
        }
        return dayItems
    }
}