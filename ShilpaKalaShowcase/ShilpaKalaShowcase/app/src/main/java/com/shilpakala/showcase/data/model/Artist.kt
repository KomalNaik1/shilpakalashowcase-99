package com.shilpakala.showcase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val craft: String,                    // e.g. "Stone Carving", "Wood Carving"
    val location: String,                 // e.g. "Shivarapatna, Karnataka"
    val bio: String,
    val experience: String,               // e.g. "25+ years"
    val specialization: String,           // e.g. "Hoysala style idols"
    val whatsappNumber: String,           // with country code e.g. "919876543210"
    val profilePhotoUri: String? = null,
    val isVerified: Boolean = true
)
