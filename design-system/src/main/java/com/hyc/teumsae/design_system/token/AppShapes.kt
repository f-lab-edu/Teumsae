package com.hyc.teumsae.design_system.token

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class AppShapes(
    val none: RoundedCornerShape = RoundedCornerShape(0.dp),
    val small1: RoundedCornerShape = RoundedCornerShape(4.dp),
    val small2: RoundedCornerShape = RoundedCornerShape(8.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(12.dp),
    val large1: RoundedCornerShape = RoundedCornerShape(16.dp),
    val large2: RoundedCornerShape = RoundedCornerShape(24.dp),
    val large3: RoundedCornerShape = RoundedCornerShape(28.dp),
    val large4: RoundedCornerShape = RoundedCornerShape(32.dp),
    val pill: RoundedCornerShape = RoundedCornerShape(999.dp),
)