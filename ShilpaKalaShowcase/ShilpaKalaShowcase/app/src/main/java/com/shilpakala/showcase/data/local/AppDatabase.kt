package com.shilpakala.showcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shilpakala.showcase.data.local.dao.ArtistDao
import com.shilpakala.showcase.data.local.dao.ArtworkDao
import com.shilpakala.showcase.data.local.dao.HeritageDao
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.model.ArtworkImage
import com.shilpakala.showcase.data.model.HeritageStory

@Database(
    entities = [Artist::class, Artwork::class, ArtworkImage::class, HeritageStory::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
    abstract fun artistDao(): ArtistDao
    abstract fun heritageDao(): HeritageDao
    companion object { const val DATABASE_NAME = "shilpa_kala_db" }
}
