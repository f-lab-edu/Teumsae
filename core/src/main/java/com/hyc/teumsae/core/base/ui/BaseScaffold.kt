package com.hyc.teumsae.core.base.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import com.hyc.teumsae.core.base.viewmodel.UiState

@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    uiState: UiState,
    blockTouchOnLoading: Boolean = true,
    appBar: @Composable () -> Unit = {},
    bottomNavigationBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = appBar,
        bottomBar = bottomNavigationBar,
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            content(paddingValues)

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
        }
    }
}