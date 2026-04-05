package com.hyc.teumsae.onboarding.presentation.viewmodel.firstsetup

import com.hyc.teumsae.core.base.viewmodel.BaseViewModel
import com.hyc.teumsae.core.base.viewmodel.NoSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class FirstSetupViewModel @Inject constructor() : BaseViewModel<FirstSetupUiIntent, FirstSetupUiState, FirstSetupUiSideEffect>() {
    private val _state = MutableStateFlow(FirstSetupUiState())
    override val state: StateFlow<FirstSetupUiState> = _state.asStateFlow()

    override fun handleIntent(intent: FirstSetupUiIntent) {
        when(intent) {
            is FirstSetupUiIntent.NextStep -> nextStep()
            is FirstSetupUiIntent.PrevStep -> prevStep()
            is FirstSetupUiIntent.SelectCategory -> selectCategory(intent.category)
            is FirstSetupUiIntent.SelectLevel -> selectLevel(intent.level)
            is FirstSetupUiIntent.SelectStartStation -> selectStation(isStart = true, intent.station)
            is FirstSetupUiIntent.SelectEndStation -> selectStation(isStart = false, intent.station)
            is FirstSetupUiIntent.SetStationSelectBottomSheet -> setStationSelectBottomSheetSheet(intent.isVisible)
        }
    }

    private fun nextStep() {
        val enableToNext = when(state.value.currentStep) {
            FirstSetupStep.ROUTE -> state.value.setRouteStepState.enableToNext
            FirstSetupStep.LEVEL -> state.value.setLevelStepState.enableToNext
            FirstSetupStep.CATEGORY -> state.value.setCategoryStepState.enableToNext
        }

        if(!enableToNext) {
            sendSideEffect(FirstSetupUiSideEffect.ShowDiableToNextStep)
            return
        }

        if(state.value.isLastStep) {
            updateSetupData()
        } else {
            _state.update {
                it.copy(
                    currentStep = it.currentStep.next()!!
                )
            }
        }
    }

    private fun prevStep() {
        if(!state.value.isFirstStep) {
            _state.update {
                it.copy(
                    currentStep = it.currentStep.prev()!!,
                    setRouteStepState = if(it.currentStep == FirstSetupStep.ROUTE) SetRouteStepState() else it.setRouteStepState,
                    setLevelStepState = if(it.currentStep == FirstSetupStep.LEVEL) SetLevelStepState() else it.setLevelStepState,
                    setCategoryStepState = if(it.currentStep == FirstSetupStep.CATEGORY) SetCategoryStepState() else it.setCategoryStepState,
                )
            }
        }
    }

    private fun updateSetupData() {

    }

    private fun selectStation(isStart: Boolean, station: String?) {
        _state.update {
            it.copy(
                setRouteStepState = it.setRouteStepState.copy(
                    startStation = if(isStart) station else it.setRouteStepState.startStation,
                    endStation = if(!isStart) station else it.setRouteStepState.endStation
                )
            )
        }
    }

    private fun selectLevel(level: Int?) {
        _state.update {
            it.copy(
                setLevelStepState = it.setLevelStepState.copy(
                    level = level
                )
            )
        }
    }

    private fun selectCategory(category: String) {
        val currentCategory = state.value.setCategoryStepState.category

        _state.update {
            it.copy(
                setCategoryStepState = it.setCategoryStepState.copy(
                    category = if(category in currentCategory) {
                        currentCategory - category
                    } else {
                        currentCategory + category
                    }
                )
            )
        }
    }

    private fun setStationSelectBottomSheetSheet(isVisible: Boolean) {
        _state.update {
            it.copy(
                setRouteStepState = it.setRouteStepState.copy(
                    showStationSelectBottomSheet = isVisible
                )
            )
        }
    }
}