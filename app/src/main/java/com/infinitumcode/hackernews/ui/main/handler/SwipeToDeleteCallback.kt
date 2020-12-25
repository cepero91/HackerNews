package com.infinitumcode.hackernews.ui.main.handler

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.infinitumcode.hackernews.R


abstract class SwipeToDeleteCallback constructor(context: Context) : ItemTouchHelper.Callback() {
  private val clearPaint: Paint = Paint()
  private val backgroundDrawable: ColorDrawable = ColorDrawable()
  private val backgroundColorInt: Int = Color.parseColor("#E53935") // Red 600
  private val deleteDrawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_baseline_delete_24)
  private var intrinsicWidth: Int = 0
  private var intrinsicHeight: Int = 0

  init {
    clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    deleteDrawable?.let {
      intrinsicWidth = deleteDrawable.intrinsicWidth
      intrinsicHeight = deleteDrawable.intrinsicHeight
    }
  }


  override fun getMovementFlags(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder): Int {
    return makeMovementFlags(0, ItemTouchHelper.LEFT)
  }

  override fun onMove(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder, @NonNull viewHolder1: RecyclerView.ViewHolder): Boolean {
    return false
  }

  override fun onChildDraw(@NonNull c: Canvas, @NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    val itemView = viewHolder.itemView
    val itemHeight = itemView.height

    val isCancelled = dX == 0f && !isCurrentlyActive

    if (isCancelled) {
      clearCanvas(c, itemView.right + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
      super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
      return
    }

    backgroundDrawable.color = backgroundColorInt
    backgroundDrawable.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
    backgroundDrawable.draw(c)

    val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
    val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
    val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
    val deleteIconRight = itemView.right - deleteIconMargin
    val deleteIconBottom = deleteIconTop + intrinsicHeight


    deleteDrawable!!.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
    deleteDrawable.draw(c)

    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)


  }

  private fun clearCanvas(c: Canvas, left: Float?, top: Float?, right: Float?, bottom: Float?) {
    c.drawRect(left!!, top!!, right!!, bottom!!, clearPaint)
  }

  override fun getSwipeThreshold(@NonNull viewHolder: RecyclerView.ViewHolder): Float {
    return 0.7f
  }
}
