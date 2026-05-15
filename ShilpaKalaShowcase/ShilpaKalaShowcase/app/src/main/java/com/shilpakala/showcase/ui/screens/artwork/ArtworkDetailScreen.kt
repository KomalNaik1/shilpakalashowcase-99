package com.shilpakala.showcase.ui.screens.artwork

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shilpakala.showcase.ui.components.InquiryButton
import com.shilpakala.showcase.ui.components.ZoomableImage
import com.shilpakala.showcase.ui.theme.GalleryBackground
import com.shilpakala.showcase.ui.theme.StoneGrey
import com.shilpakala.showcase.ui.theme.TempleGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkDetailScreen(artworkId: Int, navController: NavController, viewModel: ArtworkViewModel = hiltViewModel()) {
    LaunchedEffect(artworkId) { viewModel.loadArtwork(artworkId) }
    val artworkWrapper by viewModel.selectedArtwork.collectAsState()
    val context = LocalContext.current

    Scaffold(
        containerColor = GalleryBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(artworkWrapper?.artwork?.title ?: "Details", color = TempleGold) },
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
        artworkWrapper?.let { wrapper ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // High-quality image with Zoom capability
                Box(modifier = Modifier.fillMaxWidth().height(450.dp)) {
                    ZoomableImage(
                        imageUri = wrapper.images.firstOrNull()?.imageUri ?: "",
                        contentDescription = wrapper.artwork.title
                    )
                }

                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Product ID: ${wrapper.artwork.productId}",
                            style = MaterialTheme.typography.labelMedium,
                            color = StoneGrey
                        )
                        Text(
                            text = wrapper.artwork.status.name.replace("_", " "),
                            style = MaterialTheme.typography.labelMedium,
                            color = if (wrapper.artwork.status.name == "AVAILABLE") Color.Green else TempleGold
                        )
                    }
                    
                    Text(
                        text = wrapper.artwork.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = TempleGold,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    // Artist Info Section
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray.copy(alpha = 0.2f))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Created by:", style = MaterialTheme.typography.labelSmall, color = StoneGrey)
                            Text(wrapper.artist.name, style = MaterialTheme.typography.titleMedium, color = Color.White)
                            Text(wrapper.artist.location, style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
                        }
                    }
                    
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = StoneGrey.copy(alpha = 0.3f))
                    
                    Text(text = "Description", style = MaterialTheme.typography.titleSmall, color = TempleGold)
                    Text(
                        text = wrapper.artwork.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.LightGray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Material", style = MaterialTheme.typography.labelSmall, color = StoneGrey)
                            Text(text = wrapper.artwork.material, style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Dimensions", style = MaterialTheme.typography.labelSmall, color = StoneGrey)
                            Text(text = wrapper.artwork.dimensions, style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Estimated Price", style = MaterialTheme.typography.labelSmall, color = StoneGrey)
                    Text(
                        text = wrapper.artwork.estimatedPrice,
                        style = MaterialTheme.typography.headlineSmall,
                        color = TempleGold,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Order Inquiry: Launches WhatsApp with pre-filled message including Product ID
                    InquiryButton(artwork = wrapper.artwork, artist = wrapper.artist, context = context)
                    
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}
