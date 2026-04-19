package com.hyc.teumsae.design_system.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hyc.teumsae.design_system.theme.AppTheme

@Stable
class AppBottomSheetController {
    var isVisible by mutableStateOf(false)
        private set

    fun show() { isVisible = true }
    fun hide() { isVisible = false }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    controller: AppBottomSheetController,
    modifier: Modifier = Modifier,
    maxHeight: Dp? = null,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    sheetGesturesEnabled: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    dragHandle: @Composable (() -> Unit)? = { AppBottomSheetDragHandle() },
    content: @Composable ColumnScope.() -> Unit,
) {
    if(controller.isVisible) {
        ModalBottomSheet(
            containerColor = AppTheme.colors.neutral100,
            onDismissRequest = { if(dismissOnClickOutside) controller.hide() },
            modifier = modifier.then(
                if(maxHeight != null) Modifier.heightIn(max = maxHeight)
                else Modifier
            ),
            shape = AppTheme.shapes.large3.copy(
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            ),
            sheetState = sheetState,
            sheetGesturesEnabled = sheetGesturesEnabled,
            dragHandle = dragHandle,
            properties = ModalBottomSheetProperties(
                shouldDismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimens.s16)
            ) {
                content()
            }
        }
    }
}


@Composable
private fun AppBottomSheetDragHandle() {
    Box(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        Box(
            modifier = Modifier
                .padding(vertical = AppTheme.dimens.s16)
                .width(40.dp)
                .height(4.dp)
                .clip(CircleShape)
                .background(AppTheme.colors.neutral500)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun AppBottomSheetPreview() {
    val controller = remember {
        AppBottomSheetController().also { it.show() }
    }
    AppBottomSheet(controller = controller) {
        Text("내용")
    }
}
