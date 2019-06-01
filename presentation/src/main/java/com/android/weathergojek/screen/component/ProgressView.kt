package com.android.weathergojek.screen.component

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.ImageView
import com.android.weathergojek.R

class ProgressView : ImageView {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setAnimation(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAnimation(attrs)
    }

    constructor(context: Context) : super(context)

    private fun setAnimation(attrs: AttributeSet) {
        val styles = context.obtainStyledAttributes(attrs, R.styleable.ProgressView)
        val frameCount = styles.getInt(R.styleable.ProgressView_frameCount, 64)
        val duration = styles.getInt(R.styleable.ProgressView_duration, 1000)
        styles.recycle()
        setAnimation(frameCount, duration)
    }

    private fun setAnimation(frameCount: Int, duration: Int) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.progress_rotate)
        animation.duration = duration.toLong()
        animation.interpolator = object : Interpolator {
            override fun getInterpolation(input: Float): Float {
                return Math.floor((input * frameCount).toDouble()).toFloat() / frameCount
            }
        }
        startAnimation(animation)
    }
}
