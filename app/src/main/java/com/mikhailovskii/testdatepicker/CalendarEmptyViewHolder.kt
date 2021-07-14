package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CalendarEmptyViewHolder(view: View) : RecyclerView.ViewHolder(view),
    ItemStrategy.EmptyItemStrategy<EmptyDayItem>
