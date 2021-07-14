package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

abstract class CalendarDateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    protected var tvDate: AppCompatTextView = itemView.findViewById(R.id.tv_day_number)
    protected var tvMonth: AppCompatTextView = itemView.findViewById(R.id.tv_month)
    protected var tvDayDescription: AppCompatTextView = itemView.findViewById(R.id.tv_day_description)
    protected var clRoot: ConstraintLayout = itemView.findViewById(R.id.cl_root)
}