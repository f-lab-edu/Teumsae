package com.hyc.teumsae.core.ui.base

interface UiIntent

interface UiState {
    val isLoading: Boolean
    val error: String?
}

interface UiSideEffect

object NoSideEffect : UiSideEffect
