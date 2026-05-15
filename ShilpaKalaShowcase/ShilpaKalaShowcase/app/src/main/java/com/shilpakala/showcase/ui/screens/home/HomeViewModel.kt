package com.shilpakala.showcase.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.model.relations.ArtworkWithImages
import com.shilpakala.showcase.repository.ArtworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository
) : ViewModel() {

    val featuredArtworks: StateFlow<List<ArtworkWithImages>> = artworkRepository.getAllArtworksWithImages()
        .map { it.take(6) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val wipArtworks: StateFlow<List<Artwork>> = artworkRepository.getWorkInProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
