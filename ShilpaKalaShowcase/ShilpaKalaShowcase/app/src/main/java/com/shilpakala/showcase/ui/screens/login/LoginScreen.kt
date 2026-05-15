package com.shilpakala.showcase.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shilpakala.showcase.ui.theme.GalleryBackground
import com.shilpakala.showcase.ui.theme.StoneGrey
import com.shilpakala.showcase.ui.theme.TempleGold

@Composable
fun LoginScreen(navController: NavController) {
    var phone by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GalleryBackground)
    ) {
        // Decorative background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Black, Color.Transparent)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Shilpi Login",
                style = MaterialTheme.typography.headlineLarge,
                color = TempleGold,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Access your digital gallery and manage your masterpieces",
                style = MaterialTheme.typography.bodyMedium,
                color = StoneGrey,
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("WhatsApp Number", color = StoneGrey) },
                placeholder = { Text("e.g. 9876543210", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = TempleGold) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TempleGold,
                    unfocusedBorderColor = StoneGrey,
                    cursorColor = TempleGold,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = pin,
                onValueChange = { pin = it },
                label = { Text("Secret PIN", color = StoneGrey) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = TempleGold) },
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TempleGold,
                    unfocusedBorderColor = StoneGrey,
                    cursorColor = TempleGold,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { 
                    if (phone.isNotEmpty() && pin.isNotEmpty()) {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TempleGold),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Enter Gallery",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            TextButton(
                onClick = { /* Request Access logic */ },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("New Shilpi? Request Access", color = StoneGrey)
            }
        }
        
        Text(
            text = "PROUDLY PRESERVING INDIAN HERITAGE",
            style = MaterialTheme.typography.labelSmall,
            color = StoneGrey.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            letterSpacing = 2.sp
        )
    }
}
