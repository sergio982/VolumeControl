package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class VolumnView : View {

    var lines = 10
    var paint: Paint? = null
    var paintS: Paint? = null
    var valueIt: ValueInterface? = null

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?) : super(context) {
        init(context)
    }

    fun init(context: Context?) {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint!!.style = Paint.Style.STROKE

        paintS = Paint(Paint.ANTI_ALIAS_FLAG)
        paintS!!.style = Paint.Style.STROKE
        paintS!!.strokeWidth = 10f
        paintS!!.color = Color.BLUE
    }
    var touchX = 0.0f
    var touchY = 0.0f
    //respond to touch interaction
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //touchX = event.x

        when (event.action) {
            MotionEvent.ACTION_DOWN -> { }
            MotionEvent.ACTION_MOVE -> { touchY = event.y }
            MotionEvent.ACTION_UP -> {
            }
            else -> return false
        }

        //redraw
        invalidate()
        return true
    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val cx = (measuredWidth / 2).toFloat()
        val cy = (measuredHeight / 2).toFloat()

        paint!!.style = Paint.Style.STROKE
        paint!!.strokeWidth = 10f
        paint!!.color = Color.BLACK

        var p = 100- ((touchY)/(lines.toFloat()*20f))*100
        valueIt!!.ValueChange(p)

        for (i in 1..lines) {

            val y = i.toFloat()* 20f
            if (touchY < y) {
                canvas.drawLine(0f, y, 200f, y, paint!!)
            } else {
                canvas.drawLine(0f, y, 200f, y, paintS!!)
            }
        }

        super.onDraw(canvas)
    }

    fun setProgress(progress: Float) {
        val y = lines * 20f
        touchY = y* progress / 100
        postInvalidate()
    }

    fun setLinesS(value: Int) {
        lines = value
        postInvalidate()
    }

    fun setInterfaceClick(vi: ValueInterface) {
        valueIt = vi
    }

    interface ValueInterface {
        fun ValueChange(x: Float)
    }
}