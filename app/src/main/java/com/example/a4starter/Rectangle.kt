package com.example.a4starter

import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint

class Rectangle  // Construct a rectangle with the given dimensions
// The matrix will be used to determine location (defaults to identity matrix)
// By default, is drawn with the upper-left corner at the origin
// Assumes: width and height are positive numbers
internal constructor(var width: Float, var height: Float) {
    var x = 0f
    var y = 0f
    var matrix = Matrix() // identity matrix

    // Translate by dx, dy
    // Appends to the current matrix, so operations are cumulative
    fun translate(dx: Float, dy: Float) {
        matrix.postTranslate(dx, dy)
    }

    // Scale by sx, sy
    // Appends to the current matrix, so operations are cumulative
    fun scale(sx: Float, sy: Float) {
        matrix.postScale(sx, sy)
    }

    // Draw using the current matrix
    fun draw(canvas: Canvas, paint: Paint?) {
        val oldMatrix = canvas.matrix
        canvas.setMatrix(matrix)
        canvas.drawRect(x, y, x + width, y + height, paint!!)
        canvas.setMatrix(oldMatrix)
    }
}