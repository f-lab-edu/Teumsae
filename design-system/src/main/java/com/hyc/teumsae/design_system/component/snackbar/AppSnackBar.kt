package com.hyc.teumsae.design_system.component.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class AppSnackBarLevel {
    SUCCESS,
    INFO,
    WARNING,
    FAIL
}

private data class AppSnackBarState(
    val message: String,
    val level: AppSnackBarLevel
)

class AppSnackBarController(private val scope: CoroutineScope) {
    private var state by mutableStateOf<AppSnackBarState?>(null)

    private var dismissJob: Job? = null

    var dismissDelay: Long = 3000L
        set(value) {
            field = value.coerceAtLeast(2000L)
        }

    val message: String
        get() = _message

    val level: AppSnackBarLevel
        get() = state?.level ?: AppSnackBarLevel.INFO

    private var _message: String = ""

    val isVisible: Boolean
        get() = state != null && message.isNotEmpty()

    fun show(message: String, level: AppSnackBarLevel = AppSnackBarLevel.INFO) {
        if(state != null) return
        _message = message
        state = AppSnackBarState(message = _message, level = level)
        dismissJob?.cancel()
        dismissJob = scope.launch {
            delay(dismissDelay)
            state = null
        }
    }

    fun hide() {
        dismissJob?.cancel()
        dismissJob = null
    }
}

@Composable
fun AppSnackBar(
    controller: AppSnackBarController,
    modifier: Modifier = Modifier
) {
    val iconColor = when(controller.level) {
        AppSnackBarLevel.SUCCESS -> AppTheme.colors.successStrong
        AppSnackBarLevel.INFO -> AppTheme.colors.primary600
        AppSnackBarLevel.WARNING -> AppTheme.colors.warningStrong
        AppSnackBarLevel.FAIL -> AppTheme.colors.errorStrong
    }

    val icon = when(controller.level) {
        AppSnackBarLevel.SUCCESS -> Icons.Rounded.CheckCircle
        AppSnackBarLevel.INFO -> Icons.Rounded.Info
        AppSnackBarLevel.WARNING -> Icons.Rounded.Warning
        AppSnackBarLevel.FAIL -> Icons.Rounded.Cancel
    }

    AnimatedVisibility(
        visible = controller.isVisible,
        enter = slideInVertically { -it } + fadeIn(),
        exit = slideOutVertically { -it } + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.s24),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .background(
                        color = AppTheme.colors.neutral900,
                        shape = CircleShape
                    )
                    .padding(
                        top = AppTheme.dimens.s8,
                        bottom = AppTheme.dimens.s8,
                        start = AppTheme.dimens.s8,
                        end = AppTheme.dimens.s16
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(icon, null, tint = iconColor)
                    Spacer(modifier = Modifier.width(AppTheme.dimens.s8))
                    Text(
                        text = controller.message,
                        color = AppTheme.colors.neutral100,
                        style = AppTheme.typography.action2,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AppSnackBarPreview() {
    val scope = rememberCoroutineScope()
    val controller = remember {
        AppSnackBarController(scope).also { it.show("AppSnackBar") }
    }
    TeumsaeTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Preview") }) }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { controller.show("스낵바 메시지", level = AppSnackBarLevel.SUCCESS) }) {
                    Text("Show Snackbar")
                }
            }
        }

        AppSnackBar(
            controller = controller,
        )
    }
}