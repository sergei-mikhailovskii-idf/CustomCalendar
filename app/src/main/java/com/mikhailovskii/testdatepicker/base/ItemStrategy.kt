package com.mikhailovskii.testdatepicker.base

sealed interface ItemStrategy {
    interface EmptyItemStrategy<T : CalendarItem> : ItemStrategy

    interface NoClickItemStrategy<T : CalendarItem> : ItemStrategy {
        fun bindData(data: T)
    }

    interface DisabledItemStrategy<T : CalendarItem> : ItemStrategy {
        var onClickListener: () -> Unit

        fun bindData(data: T, selectedDay: Int)
    }

    interface EnabledItemStrategy<T : CalendarItem> : ItemStrategy {
        var onClickListener: () -> Unit

        fun bindData(data: T, selectedDay: Int)
    }
}