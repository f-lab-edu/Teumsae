package com.hyc.teumsae.design_system.theme

import androidx.compose.runtime.staticCompositionLocalOf
import com.hyc.teumsae.design_system.token.AppColors
import com.hyc.teumsae.design_system.token.AppShapes
import com.hyc.teumsae.design_system.token.AppDimens
import com.hyc.teumsae.design_system.token.AppShadows
import com.hyc.teumsae.design_system.token.AppTypography

val LocalColors = staticCompositionLocalOf { AppColors() }
val LocalTypography = staticCompositionLocalOf { AppTypography() }
val LocalShapes = staticCompositionLocalOf { AppShapes() }
val LocalDimens = staticCompositionLocalOf { AppDimens() }

val LocalShadows = staticCompositionLocalOf { AppShadows() }
