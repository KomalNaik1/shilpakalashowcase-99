package com.shilpakala.showcase.data.local.dao

import androidx.room.*
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.relations.ArtistWithArtworks
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artists ORDER BY name ASC")
    fun getAllArtists(): Flow<List<Artist>>

    @Query("SELECT * FROM artists WHERE id = :id")
    fun getArtistById(id: Int): Flow<Artist?>

    @Transaction
    @Query("SELECT * FROM artists WHERE id = :id")
    fun getArtistWithArtworks(id: Int): Flow<ArtistWithArtworks?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist): Long

    @Update
    suspend fun updateArtist(artist: Artist)
}
