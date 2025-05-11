package com.app.motel.ui

import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation

fun View.setExpandAnimation() {
    if (visibility == View.VISIBLE) return

    measure(
        View.MeasureSpec.makeMeasureSpec((parent as View).width, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    )
    val targetHeight = measuredHeight.takeIf { it > 0 } ?: run {
        // Nếu measuredHeight = 0, đợi 1 frame để đo lại
        post { setExpandAnimation() }
        return
    }


    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    layoutParams.height = 1
    visibility = View.VISIBLE
    val a: Animation = object : Animation(
    ) {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            layoutParams.height = if (interpolatedTime == 1f
            ) WindowManager.LayoutParams.WRAP_CONTENT
            else (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }


    // Expansion speed of 1dp/ms
    a.duration = (targetHeight / context.resources.displayMetrics.density).toInt().toLong()
    startAnimation(a)
}

fun View.setCollapseAnimation() {
    if (visibility != View.VISIBLE) return

    val initialHeight = measuredHeight

    val a: Animation = object : Animation(
    ) {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                visibility = View.GONE
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // Collapse speed of 1dp/ms
    a.duration =
        (initialHeight / context.resources.displayMetrics.density).toInt().toLong()
    startAnimation(a)
}
