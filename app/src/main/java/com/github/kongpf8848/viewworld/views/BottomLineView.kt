package com.github.kongpf8848.viewworld.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.max
import kotlin.math.min

class BottomLineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private lateinit var paintGray: Paint  // 背景线画笔
    private lateinit var paintGreen: Paint // 指示器画笔

    private var indicatorWidth = 40f // 指示器的固定宽度
    private var currentOffset = 0f  // 当前偏移量

    init {
        initPaint()
    }

    private fun initPaint() {
        // 背景线：灰色，圆角
        paintGray = Paint().apply {
            color = Color.GRAY
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 10f
            isAntiAlias = true
        }
        // 指示器：绿色，圆角
        paintGreen = Paint().apply {
            color = Color.parseColor("#FF00FF00")
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 10f
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerY = height / 2f
        val totalWidth = width.toFloat()

        // 1. 绘制背景线
        canvas.drawRoundRect(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            centerY,
            centerY,
            paintGray
        )

        // 2. 绘制指示器（确保不越界）
        val start = max(0f, min(currentOffset, totalWidth - indicatorWidth))
        val end = start + indicatorWidth

        if (indicatorWidth > 0) {
            canvas.drawRoundRect(
                RectF(start, 0f, end, height.toFloat()),
                centerY,
                centerY,
                paintGreen
            )
        }
    }

    /** 设置指示器物理宽度 */
    fun setIndicatorWidth(width: Float) {
        this.indicatorWidth = width
        invalidate()
    }

    /** 更新滚动比例 (0.0 ~ 1.0) */
    fun updateScrollRatio(ratio: Float) {
        val maxOffset = width - indicatorWidth
        this.currentOffset = maxOffset * ratio
        invalidate()
    }
}
