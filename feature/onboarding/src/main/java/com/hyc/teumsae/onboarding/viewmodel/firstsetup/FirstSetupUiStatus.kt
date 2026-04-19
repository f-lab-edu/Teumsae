package com.hyc.teumsae.onboarding.viewmodel.firstsetup

import com.hyc.teumsae.core.domain.model.Station
import com.hyc.teumsae.core.ui.base.UiIntent
import com.hyc.teumsae.core.ui.base.UiSideEffect
import com.hyc.teumsae.core.ui.base.UiState

data class FirstSetupUiState(
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val currentStep: FirstSetupStep = FirstSetupStep.STATION,
    val setStationStepState: SetStationStepState = SetStationStepState(),
    val setLevelStepState: SetLevelStepState = SetLevelStepState(),
    val setCategoryStepState: SetCategoryStepState = SetCategoryStepState()
) : UiState {
    val isLastStep: Boolean
        get() = currentStep.next() == null

    val isFirstStep: Boolean
        get() = currentStep.prev() == null
}

enum class FirstSetupStep {
    STATION,
    LEVEL,
    CATEGORY;

    fun next(): FirstSetupStep? = entries.getOrNull(ordinal + 1)
    fun prev(): FirstSetupStep? = entries.getOrNull(ordinal - 1)
}

data class SetStationStepState(
    val startStation: Station? = null,
    val endStation: Station? = null,
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
    data class SelectStartStation(val station: Station?): FirstSetupUiIntent
    data class SelectEndStation(val station: Station?): FirstSetupUiIntent
    data class SelectLevel(val level: Int?): FirstSetupUiIntent
    data class SelectCategory(val category: String): FirstSetupUiIntent
}


sealed interface FirstSetupUiSideEffect: UiSideEffect {
    data object ShowDiableToNextStep: FirstSetupUiSideEffect
    data object SetupComplete: FirstSetupUiSideEffect
    data object SetupFailed: FirstSetupUiSideEffect
}
