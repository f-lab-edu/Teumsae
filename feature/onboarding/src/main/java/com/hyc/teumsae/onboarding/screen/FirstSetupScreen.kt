package com.hyc.teumsae.onboarding.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hyc.teumsae.core.ui.base.BaseScaffold
import com.hyc.teumsae.core.domain.model.Station
import com.hyc.teumsae.core.domain.model.StationLine
import com.hyc.teumsae.core.ui.component.StationLineCircle
import com.hyc.teumsae.design_system.component.appbar.AppBar
import com.hyc.teumsae.design_system.component.bottomsheet.AppBottomSheet
import com.hyc.teumsae.design_system.component.bottomsheet.AppBottomSheetController
import com.hyc.teumsae.design_system.component.button.AppFillButton
import com.hyc.teumsae.design_system.component.button.AppOutlinedButton
import com.hyc.teumsae.design_system.component.snackbar.AppSnackBarController
import com.hyc.teumsae.design_system.component.snackbar.AppSnackBarLevel
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme
import com.hyc.teumsae.onboarding.component.firstsetup.SetCategoryStepContent
import com.hyc.teumsae.onboarding.component.firstsetup.SetLevelStepContent
import com.hyc.teumsae.onboarding.component.firstsetup.SetStationStepContent
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupStep
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupUiIntent
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupUiSideEffect
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupUiState
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupViewModel

@Composable
fun FirstSetupScreen(
    onNavigateToHome: () -> Unit,
    viewModel: FirstSetupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackBar = remember { AppSnackBarController(scope) }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when(effect) {
                FirstSetupUiSideEffect.ShowDiableToNextStep -> {
                    snackBar.show("입력을 완료해주세요.", AppSnackBarLevel.WARNING)
                }
                FirstSetupUiSideEffect.SetupComplete -> onNavigateToHome()
                FirstSetupUiSideEffect.SetupFailed -> {
                    snackBar.show("다시 시도해주세요.", AppSnackBarLevel.FAIL)
                }
            }
        }
    }

    FirstSetupScreenContent(
        state = state,
        snackBar = snackBar,
        onIntent = viewModel::handleIntent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstSetupScreenContent(
    state: FirstSetupUiState,
    snackBar: AppSnackBarController,
    onIntent: (FirstSetupUiIntent) -> Unit
) {
    val startStationBottomSheetController = remember { AppBottomSheetController() }
    val endStationBottomSheetController = remember { AppBottomSheetController() }

    val content = @Composable {
        when(state.currentStep) {
            FirstSetupStep.STATION -> SetStationStepContent(
                state = state.setStationStepState,
                onStartStationClick = { startStationBottomSheetController.show() },
                onEndStationClick = { endStationBottomSheetController.show() }
            )
            FirstSetupStep.LEVEL -> SetLevelStepContent(
                state = state.setLevelStepState,
                onIntent = onIntent
            )
            FirstSetupStep.CATEGORY -> SetCategoryStepContent(
                state = state.setCategoryStepState,
                onIntent = onIntent
            )
        }
    }
    
    AppBottomSheet(controller = startStationBottomSheetController) {
        StationSelectBottomSheetContent(
            title = "출발역 선택",
            onSelect = {
                onIntent(FirstSetupUiIntent.SelectStartStation(it))
                startStationBottomSheetController.hide()
            }
        )
    }

    AppBottomSheet(controller = endStationBottomSheetController) {
        StationSelectBottomSheetContent(
            title = "도착역 선택",
            onSelect = {
                onIntent(FirstSetupUiIntent.SelectEndStation(it))
                endStationBottomSheetController.hide()
            }
        )
    }

    BaseScaffold(
        appBar = {
            AppBar(
                onClose = {}
            )
        },
        snackBarController = snackBar,
        uiState = state,
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimens.s16
            )
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                content()
            }
            Row {
                if(!state.isFirstStep) {
                    AppOutlinedButton(
                        modifier = Modifier.weight(1f),
                        text = "이전",
                        onClick = { onIntent(FirstSetupUiIntent.PrevStep) }
                    )
                }

                Spacer(Modifier.width(AppTheme.dimens.s8))
                AppFillButton(
                    modifier = Modifier.weight(2f),
                    text = if(state.isLastStep) "저장" else "다음",
                    enabled = when(state.currentStep) {
                        FirstSetupStep.STATION -> state.setStationStepState.enableToNext
                        FirstSetupStep.LEVEL -> state.setLevelStepState.enableToNext
                        FirstSetupStep.CATEGORY -> state.setCategoryStepState.enableToNext
                    },
                    onClick = { onIntent(FirstSetupUiIntent.NextStep) }
                )
            }
            Spacer(Modifier.height(AppTheme.dimens.s8))
        }
    }
}



@Composable
private fun StationSelectBottomSheetContent(
    title: String,
    onSelect: (Station) -> Unit
) {
    val stationList = listOf(
        Station(name = "서울역", line = setOf(StationLine.LINE_1, StationLine.LINE_4)),
        Station(name = "시청", line = setOf(StationLine.LINE_1, StationLine.LINE_2)),
        Station(name = "종각", line = setOf(StationLine.LINE_1)),
        Station(name = "을지로입구", line = setOf(StationLine.LINE_2)),
        Station(name = "충정로", line = setOf(StationLine.LINE_2, StationLine.LINE_5)),
        Station(name = "홍대입구", line = setOf(StationLine.LINE_2, StationLine.AIRPORT)),
        Station(name = "합정", line = setOf(StationLine.LINE_2, StationLine.LINE_6)),
        Station(name = "당산", line = setOf(StationLine.LINE_2, StationLine.LINE_9)),
        Station(name = "여의도", line = setOf(StationLine.LINE_5, StationLine.LINE_9)),
        Station(name = "강남", line = setOf(StationLine.LINE_2)),
        Station(name = "교대", line = setOf(StationLine.LINE_2, StationLine.LINE_3)),
        Station(name = "고속터미널", line = setOf(StationLine.LINE_3, StationLine.LINE_7, StationLine.LINE_9)),
        Station(name = "신도림", line = setOf(StationLine.LINE_1, StationLine.LINE_2)),
        Station(name = "영등포구청", line = setOf(StationLine.LINE_2, StationLine.LINE_5)),
        Station(name = "영등포역", line = setOf(StationLine.LINE_1))
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AppText(
            text = title,
            style = AppTheme.typography.headline2.copy(
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.s8))
        // TODO: AppChip(DesignSystem) 개발 후 변경 필요
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
        ) {
            itemsIndexed(
                items = stationList
            ) { index, station ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onSelect(station) })
                        .padding(horizontal = AppTheme.dimens.s8, vertical = AppTheme.dimens.s16),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppText(
                        text = station.name,
                        style = AppTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    station.line.forEach {
                        StationLineCircle(
                            modifier = Modifier.padding(end = AppTheme.dimens.s4),
                            line = it
                        )
                    }
                }
                if(index < stationList.lastIndex) HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StationSelectBottomSheetContentPreview() {
    TeumsaeTheme {
        StationSelectBottomSheetContent(
            title = "출발역 선택",
            onSelect = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun FirstSetupScreenPreview() {
    val scope = rememberCoroutineScope()

    TeumsaeTheme {
        FirstSetupScreenContent(
            state = FirstSetupUiState(),
            onIntent = {},
            snackBar = AppSnackBarController(scope)
        )
    }
}