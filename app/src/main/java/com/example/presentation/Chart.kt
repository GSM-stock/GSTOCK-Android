package com.example.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Chart() {

}

//point representation
data class Point(val x: Float, val y: Float)

@Composable
fun testChart(modifier: Modifier) {

    // our values to draw
    val values = listOf(
        Point(0f, 1f),
        Point(1.5f, 1.2f),
        Point(2f, 0.9f),
        Point(2.5f, 2f),
        Point(3f, 1.3f),
        Point(3.5f, 3.2f),
        Point(4f, 0.8f)
    )
    // find max and min value of X, we will need that later
    val minXValues = values.minOf { it.x }
    val maxXValues = values.maxOf { it.x }

    // find max and min value of Y, we will need that later
    val minYValues = values.minOf { it.y }
    val maxYValues = values.maxOf { it.y }

    // create Box with canvas
    Box(modifier = modifier.drawBehind {
        // map data points to pixel values, in canvas we think in pixels
        val pixelPoints = values.map {
            // we use extension function to convert and scale initial values to pixels
            val x = it.x.mapValueToDifferentRange (
                inMin = minXValues,
                inMax = maxXValues,
                outMin = 0f,
                outMax = size.width
            )
            val y = it.y.mapValueToDifferentRange (
                inMin = minYValues,
                inMax = maxYValues,
                outMin = size.height,
                outMax = 0f
            )

            Point(x, y)
        }

        val path = Path() // prepare path to draw

        // in the loop below we fill our path
        pixelPoints.forEachIndexed { index, point ->
            if (index == 0) { // for the first point we just move drawing cursor to the position
                path.moveTo(point.x, point.y)
            } else {
                path.lineTo(point.x, point.y) // for rest of points we draw the line
            }
        }

        // and finally we draw the path
        drawPath(
            path,
            color = Color.Red,
            style = Stroke(width = 3f)
        )
    }) {}
}

fun Float.mapValueToDifferentRange (
    inMin: Float,
    inMax: Float,
    outMin: Float,
    outMax: Float
) = (this - inMin) * (outMax - outMin) / (inMax - inMin) + outMin

