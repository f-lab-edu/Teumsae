package com.hyc.teumsae.onboarding.viewmodel.firstsetup

import com.hyc.teumsae.core.domain.model.Station
import com.hyc.teumsae.core.domain.usecase.SetFirstSetup
import com.hyc.teumsae.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class FirstSetupViewModel @Inject constructor(
    private val setFirstSetup: SetFirstSetup
) : BaseViewModel<FirstSetupUiIntent, FirstSetupUiState, FirstSetupUiSideEffect>() {
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
        }
    }

    private fun nextStep() {
        val enableToNext = when(state.value.currentStep) {
            FirstSetupStep.STATION -> state.value.setStationStepState.enableToNext
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
                    setStationStepState = if(it.currentStep == FirstSetupStep.STATION) SetStationStepState() else it.setStationStepState,
                    setLevelStepState = if(it.currentStep == FirstSetupStep.LEVEL) SetLevelStepState() else it.setLevelStepState,
                    setCategoryStepState = if(it.currentStep == FirstSetupStep.CATEGORY) SetCategoryStepState() else it.setCategoryStepState,
                )
            }
        }
    }

    private fun updateSetupData() {
        val currentState = state.value
        val startStation = currentState.setStationStepState.startStation ?: return
        val endStation = currentState.setStationStepState.endStation ?: return
        val level = currentState.setLevelStepState.level ?: return
        val categories = currentState.setCategoryStepState.category

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            setFirstSetup(startStation, endStation, level, categories)
                .onSuccess { sendSideEffect(FirstSetupUiSideEffect.SetupComplete) }
                .onFailure { sendSideEffect(FirstSetupUiSideEffect.SetupFailed) }
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun selectStation(isStart: Boolean, station: Station?) {
        _state.update {
            it.copy(
                setStationStepState = it.setStationStepState.copy(
                    startStation = if(isStart) station else it.setStationStepState.startStation,
                    endStation = if(!isStart) station else it.setStationStepState.endStation
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
}