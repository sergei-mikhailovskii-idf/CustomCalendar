package com.mikhailovskii.testdatepicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val calendarAdapter = CalendarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvDays = findViewById<RecyclerView>(R.id.rv_days)
        rvDays.adapter = calendarAdapter
//        rvDays.addItemDecoration(
//            GridItemsDecoration(
//                this,
//                resources.getDimensionPixelSize(R.dimen.column_spacing),
//                resources.getInteger(R.integer.column_number)
//            )
//        )

//        rvDays.apply {
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
//        }

        val days = mutableListOf<DayItem>()
        val today = Calendar.getInstance()
        days.add(DayItem(Calendar.getInstance()))
        for (i in 0..29) {
            today.add(Calendar.DAY_OF_YEAR, 1)
            days.add(DayItem(Calendar.getInstance().apply {
                time = today.time
            }))
        }
        calendarAdapter.setItems(days)

    }
}