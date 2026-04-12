package com.hyc.teumsae.design_system.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppFlatCard(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.neutral100,
    shape: Shape = AppTheme.shapes.large1,
    border: Boolean = true,
    borderColor: Color = AppTheme.colors.neutral300,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(color)
            .then(if (border) Modifier.border(0.5.dp, borderColor, shape) else Modifier)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    ) {
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F9FE)
@Composable
private fun AppFlatCardPreview() {
    TeumsaeTheme {
        Column {
            AppFlatCard(modifier = Modifier.padding(16.dp)) {
                Box(modifier = Modifier.size(200.dp, 100.dp)) {
                    Text("FlatCard", modifier = Modifier.padding(16.dp))
                }
            }

            AppFlatCard(
                modifier = Modifier.padding(16.dp),
                border = false,
            ) {
                Box(modifier = Modifier.size(200.dp, 100.dp)) {
                    Text("FlatCard (no border)", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}