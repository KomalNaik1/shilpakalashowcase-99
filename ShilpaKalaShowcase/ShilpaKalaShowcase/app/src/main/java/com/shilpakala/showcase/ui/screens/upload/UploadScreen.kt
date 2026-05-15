package com.shilpakala.showcase.ui.screens.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shilpakala.showcase.data.model.ArtworkStatus
import com.shilpakala.showcase.data.model.CarvingStyle
import com.shilpakala.showcase.ui.theme.GalleryBackground
import com.shilpakala.showcase.ui.theme.TempleGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(navController: NavController, viewModel: UploadViewModel = hiltViewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var dimensions by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var selectedStyle by remember { mutableStateOf(CarvingStyle.HOYSALA) }
    var selectedStatus by remember { mutableStateOf(ArtworkStatus.AVAILABLE) }
    var completion by remember { mutableFloatStateOf(100f) }
    var selectedImages by remember { mutableStateOf<List<Uri>>(emptyList()) }

    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        selectedImages = uris
    }

    LaunchedEffect(uiState) {
        if (uiState is UploadUiState.Success) {
            navController.popBackStack()
        }
    }

    Scaffold(
        containerColor = GalleryBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Upload Masterpiece", color = TempleGold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TempleGold)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title of Artwork") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = TempleGold, focusedLabelColor = TempleGold)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description/Story") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = TempleGold, focusedLabelColor = TempleGold)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = material,
                    onValueChange = { material = it },
                    label = { Text("Material (e.g. Granite)") },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = TempleGold, focusedLabelColor = TempleGold)
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price Range") },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = TempleGold, focusedLabelColor = TempleGold)
                )
            }

            Text("Carving Style", color = TempleGold, style = MaterialTheme.typography.labelLarge)
            ScrollableTabRow(
                selectedTabIndex = selectedStyle.ordinal,
                containerColor = Color.Transparent,
                edgePadding = 0.dp,
                divider = {}
            ) {
                CarvingStyle.values().forEach { style ->
                    Tab(
                        selected = selectedStyle == style,
                        onClick = { selectedStyle = style },
                        text = { Text(style.name.replace("_", " ")) }
                    )
                }
            }

            Text("Status", color = TempleGold, style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ArtworkStatus.values().forEach { status ->
                    FilterChip(
                        selected = selectedStatus == status,
                        onClick = { selectedStatus = status },
                        label = { Text(status.name.replace("_", " ")) }
                    )
                }
            }

            if (selectedStatus == ArtworkStatus.WORK_IN_PROGRESS) {
                Text("Completion: ${completion.toInt()}%", color = TempleGold)
                Slider(
                    value = completion,
                    onValueChange = { completion = it },
                    valueRange = 0f..100f,
                    colors = SliderDefaults.colors(thumbColor = TempleGold, activeTrackColor = TempleGold)
                )
            }

            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Icon(Icons.Default.AddPhotoAlternate, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Select Photos (${selectedImages.size})")
            }

            if (uiState is UploadUiState.Error) {
                Text((uiState as UploadUiState.Error).message, color = Color.Red)
            }

            Button(
                onClick = {
                    viewModel.uploadArtwork(
                        title, description, material, dimensions, price,
                        selectedStyle, selectedStatus, completion.toInt(), selectedImages
                    )
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = title.isNotBlank() && selectedImages.isNotEmpty() && uiState !is UploadUiState.Loading,
                colors = ButtonDefaults.buttonColors(containerColor = TempleGold)
            ) {
                if (uiState is UploadUiState.Loading) {
                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(24.dp))
                } else {
                    Text("Publish Portfolio Entry", color = Color.Black)
                }
            }
        }
    }
}
