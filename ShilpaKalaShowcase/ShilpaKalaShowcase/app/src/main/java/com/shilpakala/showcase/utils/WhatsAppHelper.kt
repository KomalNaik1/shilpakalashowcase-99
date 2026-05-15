package com.shilpakala.showcase.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork

object WhatsAppHelper {
    fun sendInquiry(context: Context, artwork: Artwork, artist: Artist) {
        val message = buildString {
            appendLine("🏛️ *Shilpa-Kala Showcase — Artwork Inquiry*")
            appendLine()
            appendLine("Namaskara ${artist.name} ji,")
            appendLine()
            appendLine("I am interested in the following artwork from your gallery:")
            appendLine()
            appendLine("🆔 *Product ID:* ${artwork.productId}")  // MUST include productId
            appendLine("📿 *Artwork:* ${artwork.title}")
            appendLine("🪨 *Material:* ${artwork.material}")
            appendLine("📐 *Dimensions:* ${artwork.dimensions}")
            appendLine("💰 *Estimated Price:* ${artwork.estimatedPrice}")
            appendLine("🎨 *Style:* ${artwork.carvingStyle.name.replace("_", " ")}")
            appendLine()
            appendLine("Could you please share more details about availability, shipping, and packaging?")
            appendLine()
            appendLine("Thank you 🙏")
        }

        val encodedMessage = Uri.encode(message)
        val whatsappUri = Uri.parse("https://wa.me/${artist.whatsappNumber}?text=$encodedMessage")
        val intent = Intent(Intent.ACTION_VIEW, whatsappUri)
        intent.setPackage("com.whatsapp")

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // Fallback: open in browser
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/${artist.whatsappNumber}?text=$encodedMessage"))
            context.startActivity(browserIntent)
        }
    }
}
