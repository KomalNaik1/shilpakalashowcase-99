package com.shilpakala.showcase.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.model.ArtworkImage

data class ArtworkWithImages(
    @Embedded val artwork: Artwork,
    @Relation(parentColumn = "id", entityColumn = "artworkId")
    val images: List<ArtworkImage>
)

data class ArtworkWithArtistAndImages(
    @Embedded val artwork: Artwork,
    @Relation(parentColumn = "artistId", entityColumn = "id")
    val artist: Artist,
    @Relation(parentColumn = "id", entityColumn = "artworkId")
    val images: List<ArtworkImage>
)

data class ArtistWithArtworks(
    @Embedded val artist: Artist,
    @Relation(parentColumn = "id", entityColumn = "artistId")
    val artworks: List<Artwork>
)
