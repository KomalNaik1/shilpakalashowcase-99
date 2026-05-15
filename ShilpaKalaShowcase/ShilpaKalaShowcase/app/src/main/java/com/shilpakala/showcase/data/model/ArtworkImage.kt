package com.shilpakala.showcase.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "artwork_images",
    foreignKeys = [ForeignKey(entity = Artwork::class, parentColumns = ["id"], childColumns = ["artworkId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("artworkId")]
)
data class ArtworkImage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val artworkId: Int,
    val imageUri: String,
    val caption: String = "",
    val progressPercent: Int = 100,       // for timeline: 10%, 30%, 60%, 100%
    val takenAt: Long = System.currentTimeMillis(),
    val orderIndex: Int = 0
)
