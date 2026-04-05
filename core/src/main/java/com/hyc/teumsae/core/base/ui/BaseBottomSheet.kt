package com.hyc.teumsae.core.base.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

class BottomSheetController {
    var isVisible by mutableStateOf(false)
        private set

    fun show() { isVisible = true }
    fun hide() { isVisible = false }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    controller: BottomSheetController,
    modifier: Modifier = Modifier,
    maxHeight: Dp? = null,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    sheetGesturesEnabled: Boolean = true,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    content: @Composable ColumnScope.() -> Unit,
) {
    if(controller.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { controller.hide() },
            modifier = modifier.then(
                if(maxHeight != null) Modifier.heightIn(max = maxHeight)
                else Modifier
            ),
            sheetState = sheetState,
            sheetGesturesEnabled = sheetGesturesEnabled,
            dragHandle = dragHandle
        ) {
            content()
        }
    }
}