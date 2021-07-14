package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class CalendarHeaderViewHolder(view: View) : CalendarViewHolder<HeaderDayNameItem>(view) {

    private lateinit var tvTitle: AppCompatTextView

    override fun bindData(data: HeaderDayNameItem, selectedDayOfYear: Int) {
        tvTitle = itemView.findViewById(R.id.tv_title)
        tvTitle.text = data.name
    }

}