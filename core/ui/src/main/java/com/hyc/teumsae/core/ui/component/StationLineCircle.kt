package com.hyc.teumsae.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyc.teumsae.core.domain.model.StationLine
import com.hyc.teumsae.core.ui.foundation.toColor
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun StationLineCircle(
    modifier: Modifier = Modifier,
    line: StationLine
) {
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp)
            .background(color = line.toColor(), shape = AppTheme.shapes.pill),
        contentAlignment = Alignment.Center
    ) {
        AppText(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimens.s8,
                vertical = AppTheme.dimens.s4
            ),
            text = line.displayName,
            style = AppTheme.typography.caption1.copy(
                color = AppTheme.colors.neutral100,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Preview
@Composable
private fun StationLineCirclePreview() {
    TeumsaeTheme {
        StationLineCircle(line = StationLine.LINE_2)
    }
}