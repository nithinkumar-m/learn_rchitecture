package com.ottapp.presentation.home

import com.ottapp.core.mvi.ViewEffect
import com.ottapp.core.mvi.ViewIntent
import com.ottapp.core.mvi.ViewState
import com.ottapp.data.model.ContentCollection

/**
 * Home Screen MVI Contract
 */

sealed class HomeIntent : ViewIntent {
    object LoadContent : HomeIntent()
    data class ContentItemClicked(val itemId: String) : HomeIntent()
    object Retry : HomeIntent()
}

data class HomeState(
    val isLoading: Boolean = false,
    val collections: List<ContentCollection> = emptyList(),
    val error: String? = null
) : ViewState

sealed class HomeEffect : ViewEffect {
    data class NavigateToDetail(val itemId: String) : HomeEffect()
    data class ShowError(val message: String) : HomeEffect()
}
