package com.shilpakala.showcase.data.local

import androidx.room.TypeConverter
import com.shilpakala.showcase.data.model.ArtworkStatus
import com.shilpakala.showcase.data.model.CarvingStyle

class Converters {
    @TypeConverter
    fun fromArtworkStatus(value: ArtworkStatus): String = value.name

    @TypeConverter
    fun toArtworkStatus(value: String): ArtworkStatus = ArtworkStatus.valueOf(value)

    @TypeConverter
    fun fromCarvingStyle(value: CarvingStyle): String = value.name

    @TypeConverter
    fun toCarvingStyle(value: String): CarvingStyle = CarvingStyle.valueOf(value)
}
