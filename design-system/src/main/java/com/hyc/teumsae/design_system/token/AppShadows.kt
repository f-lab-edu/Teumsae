package com.hyc.teumsae.design_system.token

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow

@Immutable
data class AppShadows(
    val low: Shadow = Shadow(
        color = ColorPallet.blue200.copy(alpha = .4.toFloat()),
        offset = Offset(0.toFloat(), 4.toFloat()),
        blurRadius = 8.toFloat()
    ),
    val medium: Shadow = Shadow(
        color = ColorPallet.blue200.copy(alpha = .3.toFloat()),
        offset = Offset(0.toFloat(), 6.toFloat()),
        blurRadius = 12.toFloat()
    ),
    val high: Shadow = Shadow(
        color = ColorPallet.blue200.copy(alpha = .4.toFloat()),
        offset = Offset(0.toFloat(), 8.toFloat()),
        blurRadius = 16.toFloat()
    ),
)