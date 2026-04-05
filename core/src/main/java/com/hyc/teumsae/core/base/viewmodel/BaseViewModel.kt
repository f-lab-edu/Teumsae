package com.hyc.teumsae.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : UiIntent, S : UiState, E : UiSideEffect> : ViewModel() {

    abstract val state: StateFlow<S>
    abstract fun handleIntent(intent: I)

    protected val _sideEffect = Channel<E>()
    val sideEffect = _sideEffect.receiveAsFlow()

    protected fun sendSideEffect(sideEffect: E) {
        viewModelScope.launch {
            _sideEffect.send(sideEffect)
        }
    }
}

