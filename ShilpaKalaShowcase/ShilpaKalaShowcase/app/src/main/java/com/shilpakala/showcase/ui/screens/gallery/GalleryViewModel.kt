package com.shilpakala.showcase.ui.screens.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.model.ArtworkStatus
import com.shilpakala.showcase.data.model.CarvingStyle
import com.shilpakala.showcase.data.model.relations.ArtworkWithImages
import com.shilpakala.showcase.repository.ArtworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository
) : ViewModel() {

    private val _statusFilter = MutableStateFlow<ArtworkStatus?>(null)
    val statusFilter = _statusFilter.asStateFlow()

    private val _styleFilter = MutableStateFlow<CarvingStyle?>(null)
    val styleFilter = _styleFilter.asStateFlow()

    val filteredArtworks: StateFlow<List<ArtworkWithImages>> = combine(
        artworkRepository.getAllArtworksWithImages(),
        _statusFilter,
        _styleFilter
    ) { artworks, status, style ->
        artworks.filter { artworkWrapper ->
            (status == null || artworkWrapper.artwork.status == status) &&
            (style == null || artworkWrapper.artwork.carvingStyle == style)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setStatusFilter(status: ArtworkStatus?) { _statusFilter.value = status }
    fun setStyleFilter(style: CarvingStyle?) { _styleFilter.value = style }
}
