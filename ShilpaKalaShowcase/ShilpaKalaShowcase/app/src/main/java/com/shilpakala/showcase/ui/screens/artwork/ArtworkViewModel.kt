package com.shilpakala.showcase.ui.screens.artwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.model.relations.ArtworkWithArtistAndImages
import com.shilpakala.showcase.repository.ArtworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository
) : ViewModel() {

    private val _selectedArtworkId = MutableStateFlow<Int?>(null)
    
    val selectedArtwork: StateFlow<ArtworkWithArtistAndImages?> = _selectedArtworkId
        .filterNotNull()
        .flatMapLatest { id -> artworkRepository.getArtworkWithArtistAndImages(id) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun loadArtwork(id: Int) {
        _selectedArtworkId.value = id
    }
}
