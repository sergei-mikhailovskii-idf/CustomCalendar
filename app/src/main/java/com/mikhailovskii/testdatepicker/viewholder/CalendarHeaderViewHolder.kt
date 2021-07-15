package com.mikhailovskii.testdatepicker.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhailovskii.testdatepicker.HeaderDayNameItem
import com.mikhailovskii.testdatepicker.base.ItemStrategy
import com.mikhailovskii.testdatepicker.R

class CalendarHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view),
    ItemStrategy.NoClickItemStrategy<HeaderDayNameItem> {

    private lateinit var tvTitle: AppCompatTextView

    override fun bindData(data: HeaderDayNameItem) {
        tvTitle = itemView.findViewById(R.id.tv_title)
        tvTitle.text = data.name
    }

}