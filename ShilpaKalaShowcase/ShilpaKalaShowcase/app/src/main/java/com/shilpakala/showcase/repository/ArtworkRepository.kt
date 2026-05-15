package com.shilpakala.showcase.repository

import com.shilpakala.showcase.data.local.dao.ArtworkDao
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.model.ArtworkImage
import com.shilpakala.showcase.data.model.CarvingStyle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtworkRepository @Inject constructor(private val artworkDao: ArtworkDao) {
    fun getAllArtworks() = artworkDao.getAllArtworks()
    fun getAvailableArtworks() = artworkDao.getAvailableArtworks()
    fun getWorkInProgress() = artworkDao.getWorkInProgress()
    fun getArtworksByStyle(style: CarvingStyle) = artworkDao.getArtworksByStyle(style)
    fun getArtworkWithImages(id: Int) = artworkDao.getArtworkWithImages(id)
    fun getArtworkWithArtistAndImages(id: Int) = artworkDao.getArtworkWithArtistAndImages(id)
    fun getAllArtworksWithImages() = artworkDao.getAllArtworksWithImages()
    fun searchArtworks(query: String) = artworkDao.searchArtworks(query)
    suspend fun insertArtwork(artwork: Artwork): Long = artworkDao.insertArtwork(artwork)
    suspend fun insertArtworkImages(images: List<ArtworkImage>) = artworkDao.insertArtworkImages(images)
}
