package com.example.githubtrending.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface CloneableState<T> {
    fun clone(): T
}

abstract class BaseViewModel<State> : ViewModel() where State : CloneableState<State> {
    protected val _state = MutableStateFlow<State>(getInitState())
    val state: StateFlow<State>
        get() = _state

    abstract fun getInitState(): State

    protected fun updateState(update: State.() -> Unit) {
        val newState = state.value.clone()
        newState.let(update)
        _state.value = newState
    }

    protected fun getState(): State {
        return _state.value
    }
}