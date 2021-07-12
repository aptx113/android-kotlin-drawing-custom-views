/*
 * Copyright 2021 Dante Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danteyu.studio.customview.ui.codelab.clipping

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Region
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.danteyu.studio.androidkotlindrawingcustomviews.R

/**
 * Created by George Yu in 7月. 2021.
 */
private const val TEXTROW_PARAMETER = 1.5f

@Suppress("UnusedPrivateMember", "TooManyFunctions", "EmptyFunctionBlock", "MagicNumber")
class ClippedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
    }
    private val path = Path()

    // Variables for dimensions for a clipping rectangle around the whole set of shapes.
    private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)

    // Variables for the inset of a rectangle and the offset of a small rectangle.
    private val rectInset = resources.getDimension(R.dimen.rectInset)
    private val smallRectOffset = resources.getDimension(R.dimen.smallRectOffset)

    // Variable for the radius of a circle. This is the radius of the circle drawn inside the rectangle.
    private val circleRadius = resources.getDimension(R.dimen.circleRadius)

    // offset and a text size for text that is drawn inside the rectangle.
    private val textOffset = resources.getDimension(R.dimen.textOffset)
    private val textSize = resources.getDimension(R.dimen.textSize)

    // Set up the coordinates for two columns.
    private val columnOne = rectInset
    private val columnTwo = columnOne + rectInset + clipRectRight

    private val rowOne = rectInset
    private val rowTwo = rowOne + rectInset + clipRectBottom
    private val rowThree = rowTwo + rectInset + clipRectBottom
    private val rowFour = rowThree + rectInset + clipRectBottom
    private val textRow = rowFour + (TEXTROW_PARAMETER * clipRectBottom)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackAndUnClippedRectangle(canvas)
        drawDifferenceClippingExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
        // drawQuickRejectExample(canvas)
    }

    private fun drawBackAndUnClippedRectangle(canvas: Canvas) {
        canvas.drawColor(Color.GRAY)
        canvas.save()
        canvas.translate(columnOne, rowOne)
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    private fun drawDifferenceClippingExample(canvas: Canvas) {
        canvas.save()
        // Move the origin to the right for the next rectangle.
        canvas.translate(columnTwo, rowOne)
        // Use the subtraction of two clipping rectangles to create a frame.
        canvas.clipRect(
            2 * rectInset,
            2 * rectInset,
            clipRectRight - 2 * rectInset,
            clipRectBottom - 2 * rectInset
        )
        // The method clipRect(float, float, float, float, Region.Op
        // .DIFFERENCE) was deprecated in API level 26. The recommended
        // alternative method is clipOutRect(float, float, float, float),
        // which is currently available in API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipRect(
                4 * rectInset,
                4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset, Region.Op.DIFFERENCE
            )
        } else {
            canvas.clipOutRect(
                4 * rectInset,
                4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset
            )
        }
        drawClippedRectangle(canvas)
        canvas.restore()
    }

    private fun drawCircularClippingExample(canvas: Canvas) {
    }

    private fun drawIntersectionClippingExample(canvas: Canvas) {
    }

    private fun drawCombinedClippingExample(canvas: Canvas) {
    }

    private fun drawRoundedRectangleClippingExample(canvas: Canvas) {
    }

    private fun drawOutsideClippingExample(canvas: Canvas) {
    }

    private fun drawTranslatedTextExample(canvas: Canvas) {
    }

    private fun drawSkewedTextExample(canvas: Canvas) {
    }

    private fun drawQuickRejectExample(canvas: Canvas) {
    }

    private fun drawClippedRectangle(canvas: Canvas) {
        canvas.clipRect(
            rectInset,
            rectInset,
            clipRectRight,
            clipRectBottom
        )
        canvas.drawColor(Color.WHITE)

        paint.color = Color.RED
        canvas.drawLine(clipRectLeft, clipRectTop, clipRectRight, clipRectBottom, paint)

        paint.color = Color.GREEN
        canvas.drawCircle(circleRadius, clipRectBottom - circleRadius, circleRadius, paint)

        paint.color = Color.BLUE
        // Align the RIGHT side of the text with the origin.
        paint.textSize = textSize
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(context.getString(R.string.clipping), clipRectRight, textOffset, paint)
    }
}
