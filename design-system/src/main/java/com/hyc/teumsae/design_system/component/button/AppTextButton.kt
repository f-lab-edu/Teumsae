package com.hyc.teumsae.design_system.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
    color: Color? = null,
    disabledColor: Color? = null,
    enabled: Boolean = true,
    content: (@Composable () -> Unit)? = null
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = AppTheme.shapes.large1,
        colors = ButtonDefaults.textButtonColors(
            contentColor = color ?: AppTheme.colors.primary600,
            disabledContentColor = disabledColor ?: AppTheme.colors.neutral500
        ),
        contentPadding = PaddingValues(AppTheme.dimens.s4)
    ) {
        when {
            content != null -> content()
            text.isNotEmpty() -> {
                AppText(
                    text = text,
                    style = AppTheme.typography.action2.copy(
                        color = LocalContentColor.current
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppTextButtonPreview() {
    TeumsaeTheme {
        Column {
            AppTextButton(
                text = "TextButton(Enable)",
                onClick = {}
            )

            AppTextButton(
                text = "TextButton(Disable)",
                enabled = false,
                onClick = {}
            )

            AppTextButton(
                text = "TextButton(Custom Color)",
                color = AppTheme.colors.warningStrong,
                onClick = {}
            )
        }
    }
}