package com.ottapp.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ottapp.data.model.ContentCollection
import com.ottapp.data.model.TemplateType
import com.ottapp.presentation.home.components.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is HomeEffect.NavigateToDetail -> {
                    // Handle navigation
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading && state.collections.isEmpty() -> {
                    LoadingState()
                }
                state.error != null && state.collections.isEmpty() -> {
                    ErrorState(
                        message = state.error ?: "Something went wrong",
                        onRetry = { viewModel.handleIntent(HomeIntent.Retry) }
                    )
                }
                else -> {
                    HomeContent(
                        collections = state.collections,
                        onItemClick = { itemId ->
                            viewModel.handleIntent(HomeIntent.ContentItemClicked(itemId))
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeContent(
    collections: List<ContentCollection>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(collections) { collection ->
            TemplateRenderer(
                collection = collection,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
private fun TemplateRenderer(
    collection: ContentCollection,
    onItemClick: (String) -> Unit
) {
    when (collection.template) {
        TemplateType.HERO_CAROUSEL -> {
            HeroCarouselTemplate(
                collection = collection,
                onItemClick = onItemClick
            )
        }
        TemplateType.HORIZONTAL_LIST -> {
            HorizontalListTemplate(
                collection = collection,
                onItemClick = onItemClick
            )
        }
        TemplateType.VERTICAL_GRID -> {
            VerticalGridTemplate(
                collection = collection,
                onItemClick = onItemClick
            )
        }
        TemplateType.FEATURED_BANNER -> {
            FeaturedBannerTemplate(
                collection = collection,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}
