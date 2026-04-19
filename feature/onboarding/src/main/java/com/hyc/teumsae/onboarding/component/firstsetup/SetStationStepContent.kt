package com.hyc.teumsae.onboarding.component.firstsetup

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDownCircle
import androidx.compose.ui.unit.dp
import com.hyc.teumsae.core.domain.model.Station
import com.hyc.teumsae.core.domain.model.StationLine
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupUiIntent
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.SetStationStepState

@Composable
fun SetStationStepContent(
    state: SetStationStepState,
    onStartStationClick: () -> Unit,
    onEndStationClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(Modifier.height(AppTheme.dimens.s24))
        AppText(
            text = "출퇴근길을 알려주세요.",
            style = AppTheme.typography.headline1
        )
        Spacer(Modifier.height(AppTheme.dimens.s8))
        AppText(
            text = "하루 단어 학습량을 정해드릴게요.",
            style = AppTheme.typography.body2,
            color = AppTheme.colors.neutral700
        )
        Spacer(Modifier.height(AppTheme.dimens.s32))
        StationSelectBox(
            station = state.startStation?.name,
            hintText = "출발역 입력",
            onClick = onStartStationClick,
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.s16))
        StationSelectBox(
            station = state.endStation?.name,
            hintText = "도착역 입력",
            onClick = onEndStationClick,
        )
    }
}

@Composable
private fun StationSelectBox(station: String?, hintText: String, onClick: () -> Unit) {
    val isEmpty = station.isNullOrEmpty()
    val text = station ?: hintText
    val textStyle = AppTheme.typography.body1
    val emptyTextStyle = textStyle.copy(color = AppTheme.colors.neutral400)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                color = AppTheme.colors.primary600,
                width = 1.dp,
                shape = AppTheme.shapes.large1
            )
            .clickable { onClick() }
            .clip(AppTheme.shapes.large1)
            .padding(AppTheme.dimens.s16)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = text,
                style = if(isEmpty) emptyTextStyle else textStyle
            )
            Icon(
                Icons.Rounded.ArrowDropDownCircle,
                contentDescription = null,
                tint = AppTheme.colors.primary500
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun SetStationStepContentPreview() {
    SetStationStepContent(
        state = SetStationStepState(
            startStation = Station(name = "당산역", line = setOf(StationLine.LINE_2, StationLine.LINE_9))
        ),
        onStartStationClick = {},
        onEndStationClick = {}
    )
}