package com.shilpakala.showcase.ui.screens.gallery

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shilpakala.showcase.ui.components.ArtworkCard
import com.shilpakala.showcase.ui.theme.TempleGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(navController: NavController, viewModel: GalleryViewModel = hiltViewModel()) {
    val artworks by viewModel.filteredArtworks.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Gallery", color = TempleGold) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Filter chips would go here
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(artworks) { artworkWrapper ->
                    ArtworkCard(artworkWrapper.artwork) {
                        navController.navigate("artwork_detail/${artworkWrapper.artwork.id}")
                    }
                }
            }
        }
    }
}
