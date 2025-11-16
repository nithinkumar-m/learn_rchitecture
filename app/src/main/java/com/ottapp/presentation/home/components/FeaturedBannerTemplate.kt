package com.ottapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ottapp.data.model.ContentCollection

@Composable
fun FeaturedBannerTemplate(
    collection: ContentCollection,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (collection.items.isEmpty()) return
    
    val item = collection.items.first()
    
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = collection.title,
            style = MaterialTheme.typography.titleLarge
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { onItemClick(item.id) },
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.7f),
                                        Color.Transparent
                                    ),
                                    startX = 0f,
                                    endX = size.width * 0.5f
                                )
                            )
                        }
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(20.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    
                    item.description?.let { desc ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
