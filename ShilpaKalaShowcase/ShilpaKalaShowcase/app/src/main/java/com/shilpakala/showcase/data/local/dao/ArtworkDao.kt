package com.shilpakala.showcase.data.local.dao

import androidx.room.*
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.model.ArtworkImage
import com.shilpakala.showcase.data.model.CarvingStyle
import com.shilpakala.showcase.data.model.relations.ArtworkWithArtistAndImages
import com.shilpakala.showcase.data.model.relations.ArtworkWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtworkDao {
    @Query("SELECT * FROM artworks ORDER BY createdAt DESC")
    fun getAllArtworks(): Flow<List<Artwork>>

    @Query("SELECT * FROM artworks WHERE status = 'AVAILABLE' ORDER BY createdAt DESC")
    fun getAvailableArtworks(): Flow<List<Artwork>>

    @Query("SELECT * FROM artworks WHERE status = 'WORK_IN_PROGRESS' ORDER BY createdAt DESC")
    fun getWorkInProgress(): Flow<List<Artwork>>

    @Query("SELECT * FROM artworks WHERE carvingStyle = :style ORDER BY createdAt DESC")
    fun getArtworksByStyle(style: CarvingStyle): Flow<List<Artwork>>

    @Query("SELECT * FROM artworks WHERE artistId = :artistId ORDER BY createdAt DESC")
    fun getArtworksByArtist(artistId: Int): Flow<List<Artwork>>

    @Query("SELECT * FROM artworks WHERE id = :id")
    fun getArtworkById(id: Int): Flow<Artwork?>

    @Transaction
    @Query("SELECT * FROM artworks WHERE id = :id")
    fun getArtworkWithImages(id: Int): Flow<ArtworkWithImages?>

    @Transaction
    @Query("SELECT * FROM artworks WHERE id = :id")
    fun getArtworkWithArtistAndImages(id: Int): Flow<ArtworkWithArtistAndImages?>

    @Transaction
    @Query("SELECT * FROM artworks ORDER BY createdAt DESC")
    fun getAllArtworksWithImages(): Flow<List<ArtworkWithImages>>

    @Query("SELECT * FROM artworks WHERE title LIKE '%' || :query || '%' OR material LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchArtworks(query: String): Flow<List<Artwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtwork(artwork: Artwork): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworkImage(image: ArtworkImage): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworkImages(images: List<ArtworkImage>)

    @Update
    suspend fun updateArtwork(artwork: Artwork)

    @Delete
    suspend fun deleteArtwork(artwork: Artwork)
}
