package com.shilpakala.showcase.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.utils.WhatsAppHelper

@Composable
fun InquiryButton(artwork: Artwork, artist: Artist, context: Context) {
    Button(
        onClick = { WhatsAppHelper.sendInquiry(context, artwork, artist) },
        modifier = Modifier.fillMaxWidth().height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(Icons.Default.Chat, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "Enquire on WhatsApp",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}
