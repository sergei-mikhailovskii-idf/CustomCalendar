package com.mikhailovskii.testdatepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mikhailovskii.testdatepicker.base.CalendarItem
import com.mikhailovskii.testdatepicker.base.DayItem
import com.mikhailovskii.testdatepicker.utils.CalendarUtils
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var days: MutableList<DayItem>

    private val calendarAdapter = CalendarAdapter(
        onEnabledDayClicked = {
            println("enabled: ${it.date?.let(CalendarUtils::formatDate)}")
            println("diff: ${CalendarUtils.calculateDiff(days[0].date, it.date)}")
        },
        onDisabledDayClicked = {
            println("disabled: ${it.date?.let(CalendarUtils::formatDate)}")
            println("diff: ${CalendarUtils.calculateDiff(days[0].date, it.date)}")
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvDays = findViewById<RecyclerView>(R.id.rv_days)
        rvDays.adapter = calendarAdapter

        days = CalendarUtils.generateDaysBetweenTwoDates(
            Calendar.getInstance(),
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
            }
        )
        val headers = resources.getStringArray(R.array.calendar_column_headers)
        calendarAdapter.setItems(days, mutableListOf<String>().apply { addAll(headers) })
    }
}