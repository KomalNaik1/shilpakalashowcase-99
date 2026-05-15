package com.shilpakala.showcase.utils

import androidx.compose.ui.graphics.Color

object ImageUtils {
    // Generate a deterministic placeholder color from artwork ID
    fun getPlaceholderColor(id: Int): Color {
        val colors = listOf(Color(0xFF2C3E50), Color(0xFF34495E), Color(0xFF4A4A4A), Color(0xFF2D2D2D))
        return colors[id % colors.size]
    }

    // Auto-generate productId
    fun generateProductId(index: Int): String = "SKS-${index.toString().padStart(3, '0')}"
}
