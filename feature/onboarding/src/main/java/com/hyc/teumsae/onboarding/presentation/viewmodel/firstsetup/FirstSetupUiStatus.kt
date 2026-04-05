package com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup

import com.hyc.teumsae.core.base.viewmodel.UiIntent
import com.hyc.teumsae.core.base.viewmodel.UiSideEffect
import com.hyc.teumsae.core.base.viewmodel.UiState

data class FirstSetupUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val currentStep: FirstSetupStep = FirstSetupStep.ROUTE,
    val setRouteStepState: SetRouteStepState = SetRouteStepState(),
    val setLevelStepState: SetLevelStepState = SetLevelStepState(),
    val setCategoryStepState: SetCategoryStepState = SetCategoryStepState()
) : UiState {
    val isLastStep: Boolean
        get() = currentStep.next() == null

    val isFirstStep: Boolean
        get() = currentStep.prev() == null
}

enum class FirstSetupStep {
    ROUTE,
    LEVEL,
    CATEGORY;

    fun next(): FirstSetupStep? = entries.getOrNull(ordinal + 1)
    fun prev(): FirstSetupStep? = entries.getOrNull(ordinal - 1)
}

data class SetRouteStepState(
    val startStation: String? = null,
    val endStation: String? = null,
    val showStationSelectBottomSheet: Boolean = false
) {
    val enableToNext: Boolean
        get() = startStation != null && endStation != null
}

data class SetLevelStepState(
    val level: Int? = null
) {
    val enableToNext: Boolean
        get() = level != null
}

data class SetCategoryStepState(
    val category: Set<String> = emptySet()
) {
    val enableToNext: Boolean
        get() = category.isNotEmpty()
}




sealed interface FirstSetupUiIntent: UiIntent {
    data object NextStep: FirstSetupUiIntent
    data object PrevStep: FirstSetupUiIntent

    data class SetStationSelectBottomSheet(val isVisible: Boolean): FirstSetupUiIntent
    data class SelectStartStation(val station: String?): FirstSetupUiIntent
    data class SelectEndStation(val station: String?): FirstSetupUiIntent

    data class SelectLevel(val level: Int?): FirstSetupUiIntent

    data class SelectCategory(val category: String): FirstSetupUiIntent
}


sealed interface FirstSetupUiSideEffect: UiSideEffect {
    data object ShowDiableToNextStep: FirstSetupUiSideEffect
}
