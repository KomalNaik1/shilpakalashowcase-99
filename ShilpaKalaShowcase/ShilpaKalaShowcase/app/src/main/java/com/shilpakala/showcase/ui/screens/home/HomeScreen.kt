package com.shilpakala.showcase.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shilpakala.showcase.ui.components.ArtworkCard
import com.shilpakala.showcase.ui.theme.GalleryBackground
import com.shilpakala.showcase.ui.theme.StoneGrey
import com.shilpakala.showcase.ui.theme.TempleGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val featuredArtworks by viewModel.featuredArtworks.collectAsState()
    val wipArtworks by viewModel.wipArtworks.collectAsState()

    Scaffold(
        containerColor = GalleryBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("upload") },
                containerColor = TempleGold,
                contentColor = Color.Black
            ) {
                Icon(Icons.Default.Add, contentDescription = "Upload Artwork")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                HeaderSection()
            }

            item {
                SectionHeader("Featured Works") {
                    navController.navigate("gallery")
                }
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(featuredArtworks) { artworkWrapper ->
                        ArtworkCard(artworkWrapper.artwork) {
                            navController.navigate("artwork_detail/${artworkWrapper.artwork.id}")
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                SectionHeader("Crafting in Progress") {
                    navController.navigate("wip_timeline")
                }
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(wipArtworks) { artwork ->
                        ArtworkCard(artwork) {
                            navController.navigate("artwork_detail/${artwork.id}")
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Explore Heritage",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = TempleGold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    QuickNavButton(
                        icon = Icons.Default.HistoryEdu,
                        label = "Styles",
                        onClick = { navController.navigate("heritage") }
                    )
                    QuickNavButton(
                        icon = Icons.Default.PhotoLibrary,
                        label = "Gallery",
                        onClick = { navController.navigate("gallery") }
                    )
                    QuickNavButton(
                        icon = Icons.Default.HourglassEmpty,
                        label = "Timeline",
                        onClick = { navController.navigate("wip_timeline") }
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, GalleryBackground)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Shilpa-Kala Showcase",
                style = MaterialTheme.typography.headlineLarge,
                color = TempleGold
            )
            Text(
                "Ancient Art. Modern Stage.",
                style = MaterialTheme.typography.labelLarge,
                color = StoneGrey
            )
        }
    }
}

@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            color = TempleGold
        )
        TextButton(onClick = onSeeAllClick) {
            Text("See All", color = StoneGrey)
        }
    }
}

@Composable
fun QuickNavButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.DarkGray.copy(alpha = 0.5f),
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.padding(12.dp),
                tint = TempleGold
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = StoneGrey,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
