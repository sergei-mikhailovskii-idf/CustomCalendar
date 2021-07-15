package com.mikhailovskii.testdatepicker.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikhailovskii.testdatepicker.base.EmptyDayItem
import com.mikhailovskii.testdatepicker.base.ItemStrategy

class CalendarEmptyViewHolder(view: View) : RecyclerView.ViewHolder(view),
    ItemStrategy.EmptyItemStrategy<EmptyDayItem>
