package com.barrosedijanio.finanasconjuntas.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LogoImage() {
    Image(
        modifier = Modifier
            .padding(40.dp)
            .size(120.dp)
            .clip(shape = CircleShape)
            .background(Color(0xFFD9D9D9)),
        imageVector = Icons.Default.Person,
        contentDescription = "Logo"
    )
}