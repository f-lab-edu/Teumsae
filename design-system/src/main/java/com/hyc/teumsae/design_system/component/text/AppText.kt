package com.hyc.teumsae.design_system.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        overflow = overflow,
        maxLines = maxLines
    )
}


@Preview(showBackground = true)
@Composable
private fun AppTextPreview() {

    TeumsaeTheme {
        val typography = AppTheme.typography

        val typographyItems = listOf(
            "Headline1" to typography.headline1,
            "Headline2" to typography.headline2,
            "Headline3" to typography.headline3,
            "Headline4" to typography.headline4,
            "Body1" to typography.body1,
            "Body2" to typography.body2,
            "Body3" to typography.body3,
            "Action1" to typography.action1,
            "Action2" to typography.action2,
            "Action3" to typography.action3,
            "Field1" to typography.field1,
            "Field2" to typography.field2,
        )

        Column {
            typographyItems.forEach { (text, style) ->
                AppText(text = text, style = style)
            }
        }
    }
}