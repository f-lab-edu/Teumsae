package com.hyc.teumsae.onboarding.component.firstsetup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hyc.teumsae.design_system.component.card.AppFlatCard
import com.hyc.teumsae.design_system.component.text.AppText
import com.hyc.teumsae.design_system.theme.AppTheme
import com.hyc.teumsae.design_system.theme.TeumsaeTheme
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.FirstSetupUiIntent
import com.hyc.teumsae.onboarding.viewmodel.firstsetup.SetCategoryStepState

private val CATEGORIES = listOf(
    "여행" to "✈️",
    "비즈니스" to "💼",
    "일상회화" to "💬",
    "학문" to "📚",
    "기술/IT" to "💻",
    "문화/예술" to "🎨",
    "스포츠" to "⚽",
    "음식" to "🍽️",
    "자연/환경" to "🌿",
    "의료/건강" to "🏥",
)

@Composable
fun SetCategoryStepContent(
    state: SetCategoryStepState,
    onIntent: (FirstSetupUiIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(AppTheme.dimens.s24))
        AppText(
            text = "관심 카테고리를 선택해주세요.",
            style = AppTheme.typography.headline1
        )
        Spacer(Modifier.height(AppTheme.dimens.s8))
        AppText(
            text = "선택한 카테고리의 단어를 중점적으로 추천드릴게요.",
            style = AppTheme.typography.body2,
            color = AppTheme.colors.neutral700
        )
        Spacer(Modifier.height(AppTheme.dimens.s32))
        CategorySelector(
            selected = state.category,
            onSelect = { onIntent(FirstSetupUiIntent.SelectCategory(it)) },
        )
    }
}

@Composable
fun CategorySelector(
    selected: Set<String> = emptySet(),
    onSelect: (String) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.s8),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.s8),
        contentPadding = PaddingValues(0.dp),
    ) {
        items(CATEGORIES) { (label, emoji) ->
            val isSelected = label in selected
            AppFlatCard(
                modifier = Modifier.fillMaxWidth(),
                color = if (isSelected) AppTheme.colors.primary100 else AppTheme.colors.neutral100,
                borderColor = if (isSelected) AppTheme.colors.primary500 else AppTheme.colors.neutral300,
                onClick = { onSelect(label) },
            ) {
                AppText(
                    text = "$emoji  $label",
                    style = AppTheme.typography.body2,
                    color = if (isSelected) AppTheme.colors.primary600 else AppTheme.colors.neutral800,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = AppTheme.dimens.s16,
                            vertical = AppTheme.dimens.s16,
                        ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SetCategoryStepContentPreview() {
    TeumsaeTheme {
        SetCategoryStepContent(
            state = SetCategoryStepState(category = setOf("여행", "기술/IT")),
            onIntent = {}
        )
    }
}