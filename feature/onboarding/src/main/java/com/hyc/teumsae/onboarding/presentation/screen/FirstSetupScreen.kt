package com.hyc.teumsae.onboarding.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hyc.teumsae.core.base.ui.BaseScaffold
import com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup.FirstSetupUiIntent
import com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup.FirstSetupUiSideEffect
import com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup.FirstSetupUiState
import com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup.FirstSetupViewModel

@Composable
fun FirstSetupScreen(
    viewModel: FirstSetupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when(effect) {
                FirstSetupUiSideEffect.ShowDiableToNextStep -> {
                    snackBarHostState.showSnackbar("다음단계이동불가")
                }
            }
        }
    }

}

@Composable
private fun FirstSetupScreenContent(
    state: FirstSetupUiState,
    snackBarHostState: SnackbarHostState,
    onIntent: (FirstSetupUiIntent) -> Unit
) {


    BaseScaffold(
        uiState = state,
        snackBarHostState = snackBarHostState
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("당산역")
                    Text("언주역")
                }
            }
            Row {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {}
                ) {
                    Text("이전")
                }
                Button(
                    modifier = Modifier.weight(2f),
                    onClick = {}
                ) {
                    Text("다음")
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun FirstSetupScreenPreview() {
    FirstSetupScreenContent(
        state = FirstSetupUiState(),
        onIntent = {}
    )
}