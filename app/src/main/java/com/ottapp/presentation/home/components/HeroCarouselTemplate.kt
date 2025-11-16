package com.ottapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import com.ottapp.data.model.ContentCollection
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeroCarouselTemplate(
    collection: ContentCollection,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        while (true) {
            delay(5000)
            val nextPage = (pagerState.currentPage + 1) % collection.items.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            HorizontalPager(
                count = collection.items.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val item = collection.items[page]
                
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onItemClick(item.id) }
                ) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .drawWithContent {
                                drawContent()
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.8f)
                                        ),
                                        startY = size.height * 0.5f,
                                        endY = size.height
                                    )
                                )
                            }
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp)
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                        
                        item.description?.let { desc ->
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = desc,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.9f),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = { onItemClick(item.id) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Play", color = Color.Black)
                        }
                    }
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                activeColor = Color.White,
                inactiveColor = Color.White.copy(alpha = 0.5f)
            )
        }
    }
}
