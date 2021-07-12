package com.mikhailovskii.testdatepicker

import java.util.*

data class DayItem(
    val date: Calendar? = null,
    val isDateEnabled: Boolean? = null
) {

    fun isToday() = if (date != null) isSameDay(date, Calendar.getInstance()) else false

    fun isNewMonth() = date?.get(Calendar.DAY_OF_MONTH) == 1

    private fun isSameDay(cal1: Calendar, cal2: Calendar) =
        cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}
