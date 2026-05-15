package com.shilpakala.showcase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heritage_stories")
data class HeritageStory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val carvingStyle: CarvingStyle,
    val title: String,
    val periodOrigin: String,             // e.g. "11th–14th century CE"
    val geographicOrigin: String,         // e.g. "Hassan, Karnataka"
    val story: String,                    // 3–4 paragraph rich description
    val distinguishingFeatures: String,   // comma-separated key features
    val famousExamples: String,           // e.g. "Belur, Halebidu temples"
    val imageUri: String? = null
)
