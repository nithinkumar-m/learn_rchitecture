package com.ottapp.presentation.home

import com.ottapp.core.mvi.MviViewModel
import com.ottapp.data.repository.HomeRepository

class HomeViewModel(
    private val repository: HomeRepository
) : MviViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        handleIntent(HomeIntent.LoadContent)
    }

    override suspend fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadContent -> loadContent()
            is HomeIntent.Retry -> loadContent()
            is HomeIntent.ContentItemClicked -> {
                sendEffect(HomeEffect.NavigateToDetail(intent.itemId))
            }
        }
    }

    private suspend fun loadContent() {
        updateState { copy(isLoading = true, error = null) }
        
        repository.getHomeContent()
            .onSuccess { response ->
                updateState {
                    copy(
                        isLoading = false,
                        collections = response.collections,
                        error = null
                    )
                }
            }
            .onFailure { error ->
                updateState {
                    copy(
                        isLoading = false,
                        error = error.message ?: "Unknown error occurred"
                    )
                }
                sendEffect(HomeEffect.ShowError(error.message ?: "Failed to load content"))
            }
    }
}
