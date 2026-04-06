package com.hyc.teumsae.design_system.component.button

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
) {

    IconButton(
        onClick = onClick,
        modifier = modifier,
        shape = AppTheme.shapes.medium,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = AppTheme.colors.neutralLight300,
            contentColor = AppTheme.colors.neutralDark300,
        )
    ) {
        Icon(icon, null)
    }
}

@Preview(showBackground = true)
@Composable
fun AppIconButtonPreview() {
    TeumsaeTheme {
        Column {
            AppIconButton(
                icon = Icons.Filled.Apps,
                onClick = {}
            )
        }
    }
}