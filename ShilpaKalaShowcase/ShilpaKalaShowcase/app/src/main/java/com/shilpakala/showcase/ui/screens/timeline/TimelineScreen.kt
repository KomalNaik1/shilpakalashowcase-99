package com.shilpakala.showcase.ui.screens.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.ui.theme.GalleryBackground
import com.shilpakala.showcase.ui.theme.StoneGrey
import com.shilpakala.showcase.ui.theme.TempleGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(navController: NavController, viewModel: TimelineViewModel = hiltViewModel()) {
    val wipArtworks by viewModel.wipArtworks.collectAsState()

    Scaffold(
        containerColor = GalleryBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Creation Timeline", color = TempleGold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TempleGold)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black.copy(alpha = 0.9f)
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(wipArtworks) { artwork ->
                TimelineItem(artwork) {
                    navController.navigate("artwork_detail/${artwork.id}")
                }
            }
        }
    }
}

@Composable
fun TimelineItem(artwork: Artwork, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(TempleGold)
            )
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(120.dp)
                    .background(StoneGrey.copy(alpha = 0.5f))
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray.copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = artwork.title, style = MaterialTheme.typography.titleMedium, color = TempleGold)
                Text(text = "Material: ${artwork.material}", style = MaterialTheme.typography.bodySmall, color = StoneGrey)
                
                Spacer(modifier = Modifier.height(8.dp))
                
                val progress = artwork.completionPercent / 100f
                Text(
                    text = "Progress: ${artwork.completionPercent}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = TempleGold
                )
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    color = TempleGold,
                    trackColor = Color.Black
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = artwork.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray,
                    maxLines = 3
                )
            }
        }
    }
}
