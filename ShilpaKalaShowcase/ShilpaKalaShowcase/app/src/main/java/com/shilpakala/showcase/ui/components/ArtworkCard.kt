package com.shilpakala.showcase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.ui.theme.GalleryCardBg
import com.shilpakala.showcase.ui.theme.TempleGold

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArtworkCard(artwork: Artwork, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = GalleryCardBg),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(Color.DarkGray)
            ) {
                GlideImage(
                    model = artwork.thumbnailUri,
                    contentDescription = artwork.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    loading = placeholder(ColorPainter(Color.DarkGray)),
                    failure = placeholder(ColorPainter(Color.Gray))
                )
            }
            
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = artwork.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TempleGold,
                    maxLines = 1
                )
                Text(
                    text = artwork.material,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = artwork.estimatedPrice,
                        style = MaterialTheme.typography.labelLarge,
                        color = TempleGold
                    )
                    if (artwork.completionPercent < 100) {
                        Text(
                            text = "${artwork.completionPercent}%",
                            style = MaterialTheme.typography.labelSmall,
                            color = TempleGold
                        )
                    }
                }
            }
        }
    }
}
