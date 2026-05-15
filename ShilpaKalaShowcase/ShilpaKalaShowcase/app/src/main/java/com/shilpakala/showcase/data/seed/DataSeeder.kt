package com.shilpakala.showcase.data.seed

import android.util.Log
import com.shilpakala.showcase.data.local.AppDatabase
import com.shilpakala.showcase.data.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSeeder @Inject constructor(private val db: AppDatabase) {

    suspend fun seed() {
        Log.d("DataSeeder", "Starting data seeding with internet images...")
        try {
            db.clearAllTables()
            
            // 1. Seed Artist
            val artistId = db.artistDao().insertArtist(
                Artist(
                    name = "Master Ramu Shilpi",
                    craft = "Stone & Wood Carving",
                    location = "Shivarapatna, Karnataka",
                    bio = "National Award-winning master craftsman specializing in ancient Hoysala temple styles. Preserving the 1000-year-old tradition of stone carving.",
                    experience = "35 years",
                    specialization = "Hoysala & Dravidian Idols",
                    whatsappNumber = "919876543210",
                    profilePhotoUri = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?q=80&w=400"
                )
            ).toInt()

            // 2. Definitive list of artworks with high-quality Unsplash URLs
            val artworksData = listOf(
                Triple("Lord Ganesha — Hoysala Style", "Black Granite", "https://images.unsplash.com/photo-1567591414240-e2d785710649?q=80&w=1000"),
                Triple("Nataraja — Cosmic Dance", "Sandstone", "https://images.unsplash.com/photo-1590736932470-3628795c6131?q=80&w=1000"),
                Triple("Royal Elephant Carving", "Teak Wood", "https://images.unsplash.com/photo-1581333100576-b73bbe79c053?q=80&w=1000"),
                Triple("Temple Pillar Replica", "Soapstone", "https://images.unsplash.com/photo-1626245942289-408c02f06821?q=80&w=1000"),
                Triple("Goddess Lakshmi", "White Marble", "https://images.unsplash.com/photo-1614728263952-84ea256f9679?q=80&w=1000")
            )

            // General process images to fill the 20-image requirement (4 images per artwork)
            val processImages = listOf(
                "https://images.unsplash.com/photo-1566378246598-5b11a0ef486d?q=80&w=1000", // Polishing
                "https://images.unsplash.com/photo-1582555172866-f73bb12a2ab3?q=80&w=1000", // Carving tools
                "https://images.unsplash.com/photo-1533154683836-84e5ad0c1e00?q=80&w=1000"  // Raw stone
            )

            artworksData.forEachIndexed { index, (title, material, mainUri) ->
                val status = if (index % 2 == 0) ArtworkStatus.AVAILABLE else ArtworkStatus.WORK_IN_PROGRESS
                val progress = if (status == ArtworkStatus.AVAILABLE) 100 else 65
                
                val artwork = Artwork(
                    productId = "SKS-${(index + 1).toString().padStart(3, '0')}",
                    artistId = artistId,
                    title = title,
                    material = material,
                    dimensions = "24\" × 18\" × 12\"",
                    weight = "25 kg",
                    carvingStyle = CarvingStyle.HOYSALA,
                    status = status,
                    completionPercent = progress,
                    estimatedPrice = "₹${(index + 1) * 25000}",
                    description = "A masterpiece of traditional craftsmanship, meticulously carved over 4 months using ancient techniques passed down through generations.",
                    thumbnailUri = mainUri
                )
                
                val artId = db.artworkDao().insertArtwork(artwork).toInt()

                // Insert 4 images for each artwork to ensure gallery richness
                val images = mutableListOf<ArtworkImage>()
                images.add(ArtworkImage(artworkId = artId, imageUri = mainUri, caption = "Final Masterpiece", progressPercent = 100))
                
                processImages.forEachIndexed { pIndex, pUri ->
                    images.add(ArtworkImage(
                        artworkId = artId,
                        imageUri = pUri,
                        caption = "Creation Phase ${pIndex + 1}",
                        progressPercent = (pIndex + 1) * 30
                    ))
                }
                db.artworkDao().insertArtworkImages(images)
            }

            // 3. Seed Heritage Stories
            db.heritageDao().insertHeritage(HeritageStory(
                carvingStyle = CarvingStyle.HOYSALA,
                title = "The Art of Hoysala",
                periodOrigin = "11th Century",
                geographicOrigin = "Karnataka",
                story = "Known for intricate details on chloritic schist (soapstone).",
                distinguishingFeatures = "Exuberant ornamentation, stellate plans.",
                famousExamples = "Belur, Halebidu"
            ))

            Log.d("DataSeeder", "Seeding successful. 5 artworks with 20 total images added.")
        } catch (e: Exception) {
            Log.e("DataSeeder", "Seeding failed", e)
        }
    }
}
