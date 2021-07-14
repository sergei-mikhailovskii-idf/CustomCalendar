package com.mikhailovskii.testdatepicker

sealed interface ItemStrategy {
    interface EmptyItemStrategy<T : CalendarItem> : ItemStrategy

    interface NoClickItemStrategy<T : CalendarItem> : ItemStrategy {
        fun bindData(data: T)
    }

    interface DisabledItemStrategy<T : CalendarItem> : ItemStrategy {
        var onClickListener: () -> Unit

        fun bindData(data: T, selectedItemPosition: Int)
    }

    interface EnabledItemStrategy<T : CalendarItem> : ItemStrategy {
        var onClickListener: () -> Unit

        fun bindData(data: T, selectedItemPosition: Int)
    }
}