package com.hyc.teumsae.core.base.viewmodel

interface UiIntent

interface UiState {
    val isLoading: Boolean
    val error: String?
}

interface UiSideEffect

object NoSideEffect : UiSideEffect