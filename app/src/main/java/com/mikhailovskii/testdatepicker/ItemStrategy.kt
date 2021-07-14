package com.mikhailovskii.testdatepicker

sealed interface ItemStrategy {
    interface NoClickItemStrategy : ItemStrategy {
    }

    interface DisabledItemStrategy : ItemStrategy{
        var onClickListener: () -> Unit
    }
    interface EnabledItemStrategy : ItemStrategy {
        var onClickListener: () -> Unit
    }
}