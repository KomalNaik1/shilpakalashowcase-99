package com.shilpakala.showcase.ui.screens.heritage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shilpakala.showcase.ui.theme.TempleGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeritageScreen(navController: NavController, viewModel: HeritageViewModel = hiltViewModel()) {
    val stories by viewModel.heritageStories.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Heritage & Traditions", color = TempleGold) }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(stories) { story ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    onClick = { /* Navigate to detail */ }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = story.title, style = MaterialTheme.typography.titleLarge, color = TempleGold)
                        Text(text = "${story.periodOrigin} | ${story.geographicOrigin}", style = MaterialTheme.typography.labelSmall)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = story.story, maxLines = 3, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
