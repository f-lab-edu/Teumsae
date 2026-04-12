package com.hyc.teumsae.design_system.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppOutlinedButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: (@Composable () -> Unit)? = null
) {
    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        shape = AppTheme.shapes.large1,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = AppTheme.colors.primary600,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = AppTheme.colors.neutral500,
        ),
        border = BorderStroke(
            width = AppTheme.dimens.s2,
            color = if (enabled) {
                AppTheme.colors.primary600
            } else {
                AppTheme.colors.neutral500
            }
        ),
        contentPadding = PaddingValues(AppTheme.dimens.s16),
        onClick = onClick
    ) {
        if(text.isEmpty() && content != null) {
            content()
        } else {
            AppText(
                text = text,
                style = AppTheme.typography.action2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlineButtonPreview() {
    TeumsaeTheme {
        Column {
            AppOutlinedButton(
                text = "OutlineButton(Enable)",
                onClick = {}
            )
            AppOutlinedButton(
                text = "OutlineButton(Disable)",
                enabled = false,
                onClick = {}
            )
        }

    }
}