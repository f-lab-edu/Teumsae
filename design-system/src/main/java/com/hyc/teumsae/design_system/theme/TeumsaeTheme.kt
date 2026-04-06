package com.hyc.teumsae.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.hyc.teumsae.design_system.token.AppColors
import com.hyc.teumsae.design_system.token.AppShapes
import com.hyc.teumsae.design_system.token.AppDimens
import com.hyc.teumsae.design_system.token.AppTypography

@Composable
fun TeumsaeTheme(
    colors: AppColors = AppColors(),
    typography: AppTypography = AppTypography(),
    dimens: AppDimens = AppDimens(),
    shapes: AppShapes = AppShapes(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalDimens provides dimens,
        LocalShapes provides shapes,
    ) {
        MaterialTheme(
            content = content,
        )
    }
}