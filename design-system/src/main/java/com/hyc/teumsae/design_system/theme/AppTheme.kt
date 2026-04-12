package com.hyc.teumsae.design_system.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.hyc.teumsae.design_system.token.AppColors
import com.hyc.teumsae.design_system.token.AppShapes
import com.hyc.teumsae.design_system.token.AppDimens
import com.hyc.teumsae.design_system.token.AppShadows
import com.hyc.teumsae.design_system.token.AppTypography

object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimens: AppDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val shadows: AppShadows
        @Composable
        @ReadOnlyComposable
        get() = LocalShadows.current
}