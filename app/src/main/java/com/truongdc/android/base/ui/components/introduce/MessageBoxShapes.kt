package com.truongdc.android.base.ui.components.introduce

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.cos
import kotlin.math.sin


object CloudShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val firstCurveStartPointX = 0f
        val firstCurveStartPointY = size.height / 2f
        val firstCurveEndPointX = size.width / 4f
        val firstCurveEndPointY = size.height / 4f
        val secondCurveEndPointX = size.width / 2f
        val secondCurveEndPointY = 0f
        val thirdCurveEndPointX = size.width * 3f / 4f
        val thirdCurveEndPointY = size.height / 4f
        val fourthCurveEndPointX = size.width
        val fourthCurveEndPointY = size.height / 2f


        val path = Path().apply {
            moveTo(firstCurveStartPointX, firstCurveStartPointY)

            //    quadraticBezierTo(x2=size.width,y2=size.height/2, x1 = size.width/2, y1 = 0f)
            quadraticBezierTo(x2 = firstCurveEndPointX, y2 = firstCurveEndPointY, x1 = 0f, y1 = size.height / 4f)
            quadraticBezierTo(x2 = secondCurveEndPointX, y2 = secondCurveEndPointY, x1 = size.width / 4, y1 = 0f)
            quadraticBezierTo(x2 = thirdCurveEndPointX, y2 = thirdCurveEndPointY, x1 = size.width * 3f / 4f, y1 = 0f)
            quadraticBezierTo(x2 = fourthCurveEndPointX, y2 = fourthCurveEndPointY, x1 = size.width, y1 = size.height / 4f)
            // quadraticBezierTo(x2=0f, y2 = size.height/2,x1=size.width/2, y1 = size.height)
            quadraticBezierTo(x2 = size.width * 3f / 4f, y2 = size.height * 3f / 4f, x1 = size.width, y1 = size.height * 3f / 4f)
            quadraticBezierTo(x2 = size.width / 2f, y2 = size.height, x1 = size.width * 3f / 4f, y1 = size.height)
            quadraticBezierTo(x2 = size.width / 4f, y2 = size.height * 3f / 4f, x1 = size.width / 4f, y1 = size.height)
            quadraticBezierTo(x2 = 0f, y2 = size.height / 2, x1 = 0f, y1 = size.height * 3f / 4f)

            close()
        }
        return Outline.Generic(path)
    }

}

class MessageShape(private val roundedCorner: Float = 20f, private val pointerLength: Float = 50f) :
    Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width - roundedCorner, 0f)
            quadraticBezierTo(x2 = size.width, y2 = roundedCorner, x1 = size.width, y1 = 0f)
            lineTo(size.width, size.height - roundedCorner)
            quadraticBezierTo(x2 = size.width - roundedCorner, y2 = size.height, x1 = size.width, y1 = size.height)
            // lineTo(size.width/4f,size.height)
            // lineTo(size.width/8f,size.height+50f)
            // lineTo(size.width/8f,size.height)
            lineTo(roundedCorner, size.height)
            quadraticBezierTo(x2 = 0f, y2 = size.height - roundedCorner, x1 = 0f, y1 = size.height)
            lineTo(0f, size.height * 3f / 4f)
            lineTo(-pointerLength, size.height * 3f / 4f)
            lineTo(0f, size.height / 2f)
            lineTo(0f, roundedCorner)
            quadraticBezierTo(x2 = roundedCorner, y2 = 0f, x1 = 0f, y1 = 0f)
            close()
        }
        return Outline.Generic(path)
    }
}

class EllipseMessageShape(private val pointerLength: Float = 50f) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val (majorAxisLength, minorAxisLength) =
            if (size.width > size.height)
                Pair(size.width / 2f, size.height / 2)
            else if (size.height > size.width)
                Pair(size.height / 2f, size.width / 2)
            else
                Pair(size.height / 2f, size.height / 2)        //both are equal and its circle..

        val centerOfEllipse = Offset(size.width / 2f, size.height / 2f)
        val startAngle = 150f
        val startAngleInRadian = startAngle * Math.PI.toFloat() / 180f

        val xPositionOfAngle = centerOfEllipse.x + majorAxisLength * cos(startAngleInRadian)
        val yPositionOfAngle = centerOfEllipse.y + minorAxisLength * sin(startAngleInRadian)

        val rect = Rect(Offset.Zero, size)
        val path = Path().apply {
            moveTo(size.width / 4f, size.height * 3f / 4f)
            arcTo(rect = rect, startAngle, 340f, true)
            lineTo(-pointerLength, size.height * 3f / 4f + pointerLength)
            lineTo(xPositionOfAngle, yPositionOfAngle)
            close()
        }

        return Outline.Generic(path = path)
    }
}

class BubbleMessageBox(private val pointerLength: Float = 50f) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val (majorAxisLength, minorAxisLength) =
            if (size.width > size.height)
                Pair(size.width / 2, size.height / 2)
            else if (size.height > size.width)
                Pair(size.height / 2, size.width / 2)
            else
                Pair(size.height / 2, size.height / 2)        //both are equal and its circle..

        val centerOfEllipse = Offset(size.width / 2f, size.height / 2f)
        val startAngle = 100f
        val startAngleInRadian = startAngle * Math.PI.toFloat() / 180f
        val xPositionOfAngle = centerOfEllipse.x + majorAxisLength * cos(startAngleInRadian)
        val yPositionOfAngle = centerOfEllipse.y + minorAxisLength * sin(startAngleInRadian)
        val rect = Rect(Offset.Zero, size)
        val path = Path().apply {
            moveTo(size.width / 2f, size.height)
            arcTo(rect = rect, startAngle, 340f, true)
            lineTo(size.width / 2, size.height + pointerLength)
            lineTo(xPositionOfAngle, yPositionOfAngle)
            close()
        }

        return Outline.Generic(path = path)


    }
}

