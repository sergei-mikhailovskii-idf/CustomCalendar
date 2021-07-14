package com.mikhailovskii.testdatepicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OnCreateViewHolderFacade {

    fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        CalendarAdapter.CALENDAR_EMPTY_ITEM_TYPE -> onCreateCalendarEmptyViewHolder(parent)
        CalendarAdapter.CALENDAR_TODAY_DATE_ITEM_TYPE -> onCreateCalendarTodayDateViewHolder(parent)
        CalendarAdapter.CALENDAR_NEW_MONTH_DATE_ITEM_TYPE -> onCreateCalendarNewMonthDateViewHolder(parent)
        CalendarAdapter.CALENDAR_WITH_DATE_ITEM_TYPE -> onCreateCalendarWithDateViewHolder(parent)
        CalendarAdapter.CALENDAR_HEADER_ITEM_TYPE -> onCreateCalendarHeaderViewHolder(parent)
        CalendarAdapter.CALENDAR_NEW_MONTH_DISABLED_DATE_ITEM_TYPE -> onCreateCalendarNewMonthDisabledViewHolder(parent)
        else -> onCreateCalendarDisabledDateViewHolder(parent)
    }

    private fun onCreateCalendarWithDateViewHolder(parent: ViewGroup) = CalendarWithDateViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
    ) as RecyclerView.ViewHolder

    private fun onCreateCalendarEmptyViewHolder(parent: ViewGroup) = CalendarEmptyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_empty, parent, false)
    ) as RecyclerView.ViewHolder

    private fun onCreateCalendarTodayDateViewHolder(parent: ViewGroup) =
        CalendarTodayDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        ) as RecyclerView.ViewHolder

    private fun onCreateCalendarNewMonthDateViewHolder(parent: ViewGroup) =
        CalendarNewMonthDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        ) as RecyclerView.ViewHolder

    private fun onCreateCalendarNewMonthDisabledViewHolder(parent: ViewGroup) =
        CalendarNewMonthDisabledViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        ) as RecyclerView.ViewHolder

    private fun onCreateCalendarDisabledDateViewHolder(parent: ViewGroup) =
        CalendarDisabledDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        ) as RecyclerView.ViewHolder

    private fun onCreateCalendarHeaderViewHolder(parent: ViewGroup) =
        CalendarHeaderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_calendar_header, parent, false)
        ) as RecyclerView.ViewHolder

}