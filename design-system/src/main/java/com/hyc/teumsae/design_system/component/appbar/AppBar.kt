package com.hyc.teumsae.design_system.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    centerTitle: Boolean = true,
    titleContent: (@Composable () -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    val resolvedTitle: @Composable () -> Unit = {
        when {
            title != null -> {
                AppText(
                    text = title,
                    style = AppTheme.typography.headline2
                )
            }
            titleContent != null -> {
                titleContent()
            }
        }
    }

    if(centerTitle) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = resolvedTitle,
            navigationIcon = { leading?.invoke() },
            actions = { actions?.invoke(this) },
        )
    } else {
        TopAppBar(
            modifier = modifier,
            title = resolvedTitle,
            navigationIcon = { leading?.invoke() },
            actions = { actions?.invoke(this) },
        )
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    TeumsaeTheme {
        Column {
            AppBar(
                title = "AppBar",
                leading = {
                    Text("leading")
                }
            )
            AppBar(
                title = "AppBar",
                centerTitle = false,
                actions = {
                    Text("action")
                }
            )
        }
    }
}