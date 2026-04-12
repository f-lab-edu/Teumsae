package com.hyc.teumsae.design_system.component.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hyc.teumsae.design_system.component.button.AppIconButton
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.foundation.addShadow
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    showCloseIcon: Boolean = true,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    header: String? = null,
    body: @Composable () -> Unit,
    footer: (@Composable () -> Unit)?,
) {
    val maxDialogHeight = LocalWindowInfo.current.containerDpSize.height * 0.7f

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside,
        )
    ) {
        AppDialogContent(
            modifier = modifier,
            maxHeight = maxDialogHeight,
            showCloseIcon = showCloseIcon,
            onDismissRequest = onDismissRequest,
            header = header,
            body = body,
            footer = footer,
        )
    }
}

@Composable
private fun AppDialogContent(
    modifier: Modifier,
    maxHeight: Dp,
    showCloseIcon: Boolean,
    onDismissRequest: () -> Unit,
    header: String? = null,
    body: @Composable () -> Unit,
    footer: (@Composable () -> Unit)?,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.dimens.s24),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 480.dp)
                .addShadow(
                    shadow = AppTheme.shadows.high,
                    shape = AppTheme.shapes.large4
                )
        ) {
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(max = maxHeight),
                shape = AppTheme.shapes.large4,
                color = AppTheme.colors.neutral100,
                shadowElevation = 0.dp,
                tonalElevation = 0.dp,
            ) {
                if(showCloseIcon) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AppIconButton(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(
                                    top = AppTheme.dimens.s24,
                                    end = AppTheme.dimens.s24
                                ),
                            onClick = onDismissRequest,
                            icon = Icons.Rounded.Close
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(AppTheme.dimens.s24)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = AppTheme.dimens.s4),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (header != null) {
                                AppText(
                                    text = header,
                                    style = AppTheme.typography.headline2
                                )
                            }
                        }
                        if(showCloseIcon) Spacer(modifier = Modifier.size(48.dp))
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .verticalScroll(scrollState)
                    ) {
                        body()
                    }

                    footer?.invoke()
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PHONE, showSystemUi = true)
@Composable
private fun AppDialogPreview() {
    TeumsaeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AppDialogContent(
                modifier = Modifier,
                maxHeight = 600.dp,
                showCloseIcon = true,
                onDismissRequest = {},
                header = "HeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeader",
                body = { Text("Body") },
                footer = { Text("Footer") }
            )
        }
    }
}