package com.example.a4starter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.*
import android.util.Log
import java.util.Timer
import java.util.*
import java.util.TimerTask
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer

class CanvasView: View {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    var grey_brush = Paint(Paint.ANTI_ALIAS_FLAG)
    var rectangle = Rectangle(550f, 300f)
    var p1_id = 0
    var p1_index = 0
    var inverse = Matrix()
    var currentMatrix = Matrix()
    var x1 = 0f
    var y1 = 0f
    var path:Path? = null
    var paths:ArrayList<Path?> = ArrayList()
    var paintbrush = Paint(Color.BLUE)
    var point:ArrayList<Int> = ArrayList<Int>()
    var pointList:ArrayList<ArrayList<Int>> = ArrayList<ArrayList<Int>>()
    var index:Int = 0
    var canDraw = true


    init {
        grey_brush.color = Color.GRAY

        rectangle.scale(4f, 3f)
        rectangle.translate(0f, 200f)

        paintbrush.style = Paint.Style.STROKE
        paintbrush.strokeWidth = 5f
    }

    override fun setOnTouchListener(l: OnTouchListener) {
        super.setOnTouchListener(l)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        /*if(canDraw){
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //check boundary
                    Log.d("panzoom", "Action down")
                    x1 = event.x
                    y1 = event.y
                    Log.d("panzoom", x1.toString())
                    Log.d("panzoom", y1.toString())
                    point.add(x1.toInt())
                    point.add(y1.toInt())
                    pointList.add(point)
                    ++index
                }
                MotionEvent.ACTION_MOVE -> {
    //                val fixedRateTimer = fixedRateTimer("time", true, 0, 100) {
                        Log.d("panzoom", "Action move")
                        //check boundary
                        x1 = event.x
                        y1 = event.y
                        point.add(x1.toInt())
                        point.add(y1.toInt())
                        pointList.add(point)
                        ++index
    //                }
                }
                MotionEvent.ACTION_UP -> {
                    Log.d("panzoom", "Action up, done")
                    canDraw = false
                }
            }
        }*/

        var inverted = floatArrayOf()

        p1_id = event.getPointerId(0)
        p1_index = event.findPointerIndex(p1_id)
        inverse = Matrix()
        currentMatrix.invert(inverse)

        inverted = floatArrayOf(event.getX(p1_index),event.getY(p1_index))
        inverse.mapPoints(inverted)
        x1 = inverted[0]
        y1 = inverted[1]
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("panzoom", "Action down")
                path = Path()
                paths.add(path)
                path!!.moveTo(x1,y1)
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("panzoom", "Action move")
                path!!.lineTo(x1,y1)
            }
            MotionEvent.ACTION_UP -> Log.d("panzoom", "Action up")
        }


        return true
    }
    var canvas = Canvas()
    //timer.scheduleAtFixedRate(performanceClass(), 1000)
    //timer.scheduleAtFixedRate(task, 0, 1000)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rectangle.draw(canvas, grey_brush)
        canvas.concat(currentMatrix)
        for (path in paths) {
            canvas.drawPath(path!!, paintbrush)
        }
    }
}


