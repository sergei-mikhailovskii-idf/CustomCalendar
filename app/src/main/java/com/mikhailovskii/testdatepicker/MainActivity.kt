package com.mikhailovskii.testdatepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val calendarAdapter = CalendarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvDays = findViewById<RecyclerView>(R.id.rv_days)
        rvDays.adapter = calendarAdapter

        val days = mutableListOf<DayItem>()
        for (i in 0..30) {
            days.add(DayItem())
        }
        calendarAdapter.setItems(days)

    }
}