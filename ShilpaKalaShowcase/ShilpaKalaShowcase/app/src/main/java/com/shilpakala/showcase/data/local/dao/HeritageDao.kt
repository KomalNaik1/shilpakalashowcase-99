package com.shilpakala.showcase.data.local.dao

import androidx.room.*
import com.shilpakala.showcase.data.model.CarvingStyle
import com.shilpakala.showcase.data.model.HeritageStory
import kotlinx.coroutines.flow.Flow

@Dao
interface HeritageDao {
    @Query("SELECT * FROM heritage_stories ORDER BY title ASC")
    fun getAllHeritageStories(): Flow<List<HeritageStory>>

    @Query("SELECT * FROM heritage_stories WHERE carvingStyle = :style LIMIT 1")
    fun getHeritageByStyle(style: CarvingStyle): Flow<HeritageStory?>

    @Query("SELECT * FROM heritage_stories WHERE id = :id")
    fun getHeritageById(id: Int): Flow<HeritageStory?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeritage(story: HeritageStory): Long
}
