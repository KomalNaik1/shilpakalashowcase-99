package com.shilpakala.showcase.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "artworks",
    foreignKeys = [ForeignKey(entity = Artist::class, parentColumns = ["id"], childColumns = ["artistId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("artistId")]
)
data class Artwork(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: String,                // e.g. "SKS-001", "SKS-002" — used in WhatsApp inquiry
    val artistId: Int,
    val title: String,
    val description: String,
    val material: String,                 // e.g. "Black Granite", "Sandstone", "Teak Wood"
    val dimensions: String,               // e.g. "24 inches x 12 inches"
    val weight: String,                   // e.g. "15 kg"
    val carvingStyle: CarvingStyle,
    val status: ArtworkStatus,
    val estimatedPrice: String,           // e.g. "₹45,000 - ₹55,000"
    val completionPercent: Int = 100,     // for WIP artworks, 0-100
    val createdAt: Long = System.currentTimeMillis(),
    val thumbnailUri: String? = null      // first/primary image
)

enum class ArtworkStatus {
    AVAILABLE,          // Ready for sale, fully completed
    WORK_IN_PROGRESS,   // Still being carved
    SOLD,               // Already sold
    COMMISSIONED        // Custom order in progress
}

enum class CarvingStyle {
    HOYSALA,
    DRAVIDIAN,
    CHALUKYA,
    VIJAYANAGARA,
    CONTEMPORARY,
    WOOD_TRADITIONAL,
    CUSTOM
}
