package com.hyc.teumsae.design_system.component.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.component.button.AppIconButton
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    centerTitle: Boolean = true,
    onBack: (() -> Unit)? = null,
    onClose: (() -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    val resolvedLeading: @Composable () -> Unit = {
        when {
            onBack != null -> AppIconButton(icon = Icons.AutoMirrored.Rounded.ArrowBack, onClick = onBack)
            else -> leading?.invoke()
        }
    }

    val resolvedActions: @Composable RowScope.() -> Unit = {
        when {
            onClose != null -> AppIconButton(icon = Icons.Rounded.Close, onClick = onClose)
            else -> actions?.invoke(this)
        }
    }

    if (centerTitle) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = title,
            navigationIcon = resolvedLeading,
            actions = resolvedActions,
        )
    } else {
        TopAppBar(
            modifier = modifier,
            title = title,
            navigationIcon = resolvedLeading,
            actions = resolvedActions,
        )
    }
}

@Composable
fun AppBar(
    title: String,
    modifier: Modifier = Modifier,
    centerTitle: Boolean = true,
    onBack: (() -> Unit)? = null,
    onClose: (() -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    AppBar(
        modifier = modifier,
        title = { AppText(text = title, style = AppTheme.typography.headline2) },
        centerTitle = centerTitle,
        onBack = onBack,
        onClose = onClose,
        leading = leading,
        actions = actions,
    )
}

@Preview
@Composable
private fun AppBarPreview() {
    TeumsaeTheme {
        Column {
            AppBar(title = "뒤로가기", onBack = {})
            AppBar(title = "닫기", onClose = {})
            AppBar(title = { AppText("슬롯 타이틀", style = AppTheme.typography.headline2) })
        }
    }
}
