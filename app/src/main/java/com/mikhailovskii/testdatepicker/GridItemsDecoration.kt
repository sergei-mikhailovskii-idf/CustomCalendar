package com.mikhailovskii.testdatepicker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class GridItemsDecoration(
    private val context: Context,
    private val spacingSize: Int,
    private val columnsAmount: Int,
    private var isNeedLeftSpacing: Boolean = false,
) : RecyclerView.ItemDecoration() {

    private var dividerDrawable = ContextCompat.getDrawable(context, R.drawable.divider)


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val frameWidth =
            (parent.width - (spacingSize * (columnsAmount - 1)).toFloat() / columnsAmount)
        val padding = (parent.width / columnsAmount - frameWidth).toInt()
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        outRect.top = if (itemPosition < columnsAmount) 0 else spacingSize
        when {
            itemPosition % columnsAmount == 0 -> {
                outRect.left = 0
                outRect.right = padding
                isNeedLeftSpacing = true
            }
            (itemPosition + 1) % columnsAmount == 0 -> {
                isNeedLeftSpacing = false
                outRect.left = spacingSize - padding
                outRect.right = if ((itemPosition + 2) % columnsAmount == 0) {
                    spacingSize - padding
                } else {
                    spacingSize / 2
                }
            }
            (itemPosition + 2) % columnsAmount == 0 -> {
                isNeedLeftSpacing = false
                outRect.left = spacingSize / 2
                outRect.right = spacingSize - padding
            }
            else -> {
                isNeedLeftSpacing = false
                outRect.left = spacingSize / 2
                outRect.right = spacingSize / 2
            }
        }
        outRect.bottom = 0
    }

//    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDrawOver(c, parent, state)
//        val left = parent.paddingLeft
//        val right = parent.width - parent.paddingRight
//        val childCount = parent.childCount
//        for (i in 0 until childCount) {
//            val child: View = parent.getChildAt(i)
//            val params = child.layoutParams as RecyclerView.LayoutParams
//            val top: Int = child.bottom + params.bottomMargin
//            val bottom = top + (dividerDrawable?.intrinsicHeight ?: 0)
//            dividerDrawable?.setBounds(left, top, right, bottom)
//            dividerDrawable?.draw(c)
//        }
//    }

}