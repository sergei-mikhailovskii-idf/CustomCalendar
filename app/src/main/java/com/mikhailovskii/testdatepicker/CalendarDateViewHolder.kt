package com.mikhailovskii.testdatepicker

import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout

abstract class CalendarDateViewHolder(view: View) : CalendarViewHolder<DayItem>(view) {

    protected lateinit var tvDate: AppCompatTextView
    protected lateinit var tvMonth: AppCompatTextView
    protected lateinit var tvDayDescription: AppCompatTextView
    protected lateinit var clRoot: ConstraintLayout

    @CallSuper
    override fun bindData(data: DayItem, selectedDayOfYear: Int) {
        tvDate = itemView.findViewById(R.id.tv_day_number)
        tvMonth = itemView.findViewById(R.id.tv_month)
        tvDayDescription = itemView.findViewById(R.id.tv_day_description)
        clRoot = itemView.findViewById(R.id.cl_root)
    }
}