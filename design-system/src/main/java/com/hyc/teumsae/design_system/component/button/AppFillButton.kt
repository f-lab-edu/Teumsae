package com.hyc.teumsae.design_system.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppFillButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: (@Composable () -> Unit)? = null
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = AppTheme.shapes.large1,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.highlight500,
            contentColor = AppTheme.colors.neutralLight100,
            disabledContainerColor = AppTheme.colors.neutralLight300,
            disabledContentColor = AppTheme.colors.neutralLight100,
        ),
        contentPadding = PaddingValues(AppTheme.dimens.s16)
    ) {
        if(text.isEmpty() && content != null) {
            content()
        } else {
            AppText(
                text = text,
                style = AppTheme.typography.action2.copy(
                    color = LocalContentColor.current
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilledButtonPreview() {
    TeumsaeTheme {
        Column {
            AppFillButton(
                text = "Fill Button(Enable)",
                onClick = {}
            )
            AppFillButton(
                text = "Fill Button(Disable)",
                enabled = false,
                onClick = {}
            )
        }
    }
}