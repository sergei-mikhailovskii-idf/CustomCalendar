package com.mikhailovskii.testdatepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val calendarAdapter = CalendarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvDays = findViewById<RecyclerView>(R.id.rv_days)
        rvDays.adapter = calendarAdapter

        val days = mutableListOf<DayItem>()
        val today = Calendar.getInstance()
        days.add(DayItem(Calendar.getInstance(), false))
        for (i in 0..29) {
            today.add(Calendar.DAY_OF_YEAR, 1)

            // i % 4 != 0 - stub for disabled
            days.add(DayItem(Calendar.getInstance().apply {
                time = today.time
            }, i % 4 != 0))
        }
        val headers = resources.getStringArray(R.array.calendar_column_headers)
        calendarAdapter.setItems(days, mutableListOf<String>().apply { addAll(headers) })

    }
}