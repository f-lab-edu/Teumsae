package com.hyc.teumsae.design_system.foundation

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb


fun Modifier.addShadow(
    shadow: Shadow,
    shape: Shape,
): Modifier = this.dropShadow(
    shape = shape,
) {
    radius = shadow.blurRadius
    color = shadow.color
    offset = shadow.offset
}