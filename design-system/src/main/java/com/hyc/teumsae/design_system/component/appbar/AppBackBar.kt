package com.hyc.teumsae.design_system.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.component.button.AppIconButton
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppBackBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBack: () -> Unit,
) {
    AppBar(
        modifier = modifier,
        title = title,
        leading = {
            AppIconButton(
                icon = Icons.AutoMirrored.Rounded.ArrowBack,
                onClick = onBack
            )
        }
    )
}

@Preview
@Composable
private fun AppBackBarPreview() {
    TeumsaeTheme {
        Column {
            AppBackBar(
                title = "AppBackBar",
                onBack = {}
            )
            AppBackBar(
                onBack = {}
            )
        }
    }
}