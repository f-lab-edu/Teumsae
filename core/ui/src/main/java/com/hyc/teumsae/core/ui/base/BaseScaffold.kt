package com.hyc.teumsae.core.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import com.hyc.teumsae.design_system.component.snackbar.AppSnackBar
import com.hyc.teumsae.design_system.component.snackbar.AppSnackBarController

@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    uiState: UiState,
    snackBarController: AppSnackBarController? = null,
    blockTouchOnLoading: Boolean = true,
    appBar: @Composable () -> Unit = {},
    bottomNavigationBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = appBar,
        bottomBar = bottomNavigationBar,
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                content()
            }

            if(uiState.isLoading) {
                if(blockTouchOnLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                awaitPointerEventScope {
                                    while (true) {
                                        awaitPointerEvent(pass = PointerEventPass.Initial)
                                    }
                                }
                            }
                    )
                }

                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            snackBarController?.let {
                AppSnackBar(
                    controller = it,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}
