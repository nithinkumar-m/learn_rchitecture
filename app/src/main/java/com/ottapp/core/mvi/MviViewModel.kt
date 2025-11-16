package com.ottapp.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel for MVI architecture
 * @param S State type
 * @param I Intent type
 * @param E Effect type
 */
abstract class MviViewModel<S : ViewState, I : ViewIntent, E : ViewEffect>(
    initialState: S
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = Channel<E>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    /**
     * Handle user intents
     */
    fun handleIntent(intent: I) {
        viewModelScope.launch {
            processIntent(intent)
        }
    }

    /**
     * Process the intent and update state
     */
    protected abstract suspend fun processIntent(intent: I)

    /**
     * Update the current state
     */
    protected fun updateState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }

    /**
     * Send a one-time effect
     */
    protected suspend fun sendEffect(effect: E) {
        _effect.send(effect)
    }

    /**
     * Get current state value
     */
    protected val currentState: S
        get() = _state.value
}

/**
 * Marker interface for View States
 */
interface ViewState

/**
 * Marker interface for View Intents (User Actions)
 */
interface ViewIntent

/**
 * Marker interface for View Effects (One-time events)
 */
interface ViewEffect
