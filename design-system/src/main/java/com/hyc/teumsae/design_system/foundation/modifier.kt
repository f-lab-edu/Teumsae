package com.hyc.teumsae.design_system.foundation

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape

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