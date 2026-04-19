package com.hyc.teumsae.onboarding.component.firstsetup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.commandiron.wheel_picker_compose.core.WheelTextPicker
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupUiIntent
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.SetLevelStepState


@Composable
fun SetLevelStepContent(
    state: SetLevelStepState,
    onIntent: (FirstSetupUiIntent) -> Unit
) {
    data class LevelInfo(
        val label: String,
        val description: String
    )

    val levels = listOf(
        LevelInfo("입문", "영어 공부를 이제 막 시작하는 단계에요."),
        LevelInfo("초급", "기본적인 영단어를 알고 일상 표현을 이해해요."),
        LevelInfo("중급", "다양한 주제의 어휘를 이해하고 활용할 수 있어요."),
        LevelInfo("고급", "전문적인 어휘까지 폭넓게 이해하고 사용해요."),
    )

    val selectedIndex = (state.level?.minus(1))?.coerceIn(0, levels.lastIndex) ?: 0
    val selectedLevel = levels[selectedIndex]

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(AppTheme.dimens.s24))
        AppText(
            modifier = Modifier.fillMaxWidth(),
            text = "영어 실력을 알려주세요.",
            style = AppTheme.typography.headline1
        )
        Spacer(Modifier.height(AppTheme.dimens.s8))
        AppText(
            modifier = Modifier.fillMaxWidth(),
            text = "영어 실력에 맞는 단어를 추천드릴게요.",
            style = AppTheme.typography.body2,
            color = AppTheme.colors.neutral700
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                WheelTextPicker(
                    texts = listOf("") + levels.map { it.label } + listOf(""),
                    rowCount = 3,
                    size = DpSize(240.dp, 240.dp),
                    startIndex = selectedIndex + 1,
                    style = AppTheme.typography.headline1,
                    color = AppTheme.colors.neutral900,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        color = AppTheme.colors.primary100,
                        border = BorderStroke(
                            width = 1.dp,
                            color = AppTheme.colors.primary400
                        )
                    ),
                    onScrollFinished = { index ->
                        val adjusted = (index - 1).coerceIn(0, levels.lastIndex)
                        onIntent(FirstSetupUiIntent.SelectLevel(adjusted + 1))
                        null
                    },
                )
                Spacer(Modifier.height(AppTheme.dimens.s24))
                AppText(
                    text = selectedLevel.description,
                    style = AppTheme.typography.body2,
                    color = AppTheme.colors.neutral600
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SetLevelStepContentPreview() {
    SetLevelStepContent(
        state = SetLevelStepState(level = 2),
        onIntent = {}
    )
}
