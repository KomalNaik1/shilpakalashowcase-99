package com.shilpakala.showcase.ui.screens.upload

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.model.ArtworkImage
import com.shilpakala.showcase.data.model.ArtworkStatus
import com.shilpakala.showcase.data.model.CarvingStyle
import com.shilpakala.showcase.repository.ArtworkRepository
import com.shilpakala.showcase.data.local.dao.ArtistDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val artistDao: ArtistDao
) : ViewModel() {

    private val _uiState = MutableStateFlow<UploadUiState>(UploadUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun uploadArtwork(
        title: String,
        description: String,
        material: String,
        dimensions: String,
        price: String,
        style: CarvingStyle,
        status: ArtworkStatus,
        completion: Int,
        imageUris: List<Uri>
    ) {
        viewModelScope.launch {
            _uiState.value = UploadUiState.Loading
            try {
                val artists = artistDao.getAllArtists().first()
                if (artists.isEmpty()) {
                    _uiState.value = UploadUiState.Error("No artist found. Please wait for data seeding.")
                    return@launch
                }
                
                val artistId = artists.first().id
                val productId = "SKS-${System.currentTimeMillis().toString().takeLast(4)}"
                
                val artwork = Artwork(
                    productId = productId,
                    artistId = artistId,
                    title = title,
                    description = description,
                    material = material,
                    dimensions = dimensions,
                    weight = "Unknown",
                    carvingStyle = style,
                    status = status,
                    estimatedPrice = price,
                    completionPercent = completion,
                    thumbnailUri = imageUris.firstOrNull()?.toString()
                )
                
                val artworkId = artworkRepository.insertArtwork(artwork).toInt()
                
                val images = imageUris.mapIndexed { index, uri ->
                    ArtworkImage(
                        artworkId = artworkId,
                        imageUri = uri.toString(),
                        orderIndex = index,
                        progressPercent = if (status == ArtworkStatus.WORK_IN_PROGRESS) completion else 100
                    )
                }
                artworkRepository.insertArtworkImages(images)
                
                _uiState.value = UploadUiState.Success
            } catch (e: Exception) {
                _uiState.value = UploadUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class UploadUiState {
    object Idle : UploadUiState()
    object Loading : UploadUiState()
    object Success : UploadUiState()
    data class Error(val message: String) : UploadUiState()
}
