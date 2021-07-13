package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CalendarViewHolder<T : CalendarItem>(view: View) :
    RecyclerView.ViewHolder(view) {

    open fun bindData(data: T) {}
}