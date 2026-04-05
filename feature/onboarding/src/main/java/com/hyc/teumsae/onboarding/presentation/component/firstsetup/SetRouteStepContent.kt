package com.hyc.teumsae.onboarding.presentation.component.firstsetup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup.FirstSetupUiIntent
import com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup.SetRouteStepState

@Composable
fun SetRouteStepContent(
    state: SetRouteStepState,
    onIntent: (FirstSetupUiIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("SetRouteStepContent")
    }
}

@Preview(showBackground = true)
@Composable
private fun SetRouteStepContentPreview() {
    SetRouteStepContent(
        state = SetRouteStepState(),
        onIntent = {}
    )
}