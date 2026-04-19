package com.hyc.teumsae.design_system.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyc.teumsae.design_system.component.button.AppIconButton
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppCloseBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onClose: () -> Unit,
) {
    AppBar(
        modifier = modifier,
        title = title,
        centerTitle = false,
        actions = {
            AppIconButton(
                modifier = Modifier.padding(end = AppTheme.dimens.s8),
                icon = Icons.Rounded.Close,
                onClick = onClose
            )
        }
    )
}

@Preview
@Composable
private fun AppCloseBarPreview() {
    TeumsaeTheme {
        Column {
            AppCloseBar(
                title = "AppCloseBar",
                onClose = {}
            )
            AppCloseBar(
                onClose = {}
            )
        }
    }
}