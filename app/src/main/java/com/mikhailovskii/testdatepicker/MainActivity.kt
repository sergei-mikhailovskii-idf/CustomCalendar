package com.mikhailovskii.testdatepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mikhailovskii.testdatepicker.utils.CalendarUtils
import java.util.*

class MainActivity : AppCompatActivity() {

    private val calendarAdapter = CalendarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvDays = findViewById<RecyclerView>(R.id.rv_days)
        rvDays.adapter = calendarAdapter

        val days = CalendarUtils.generateDaysBetweenTwoDates(
            Calendar.getInstance(),
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
                add(Calendar.DAY_OF_MONTH, -1)
            }
        )
        val headers = resources.getStringArray(R.array.calendar_column_headers)
        calendarAdapter.setItems(days, mutableListOf<String>().apply { addAll(headers) })
    }
}