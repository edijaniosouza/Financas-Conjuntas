package com.barrosedijanio.finanasconjuntas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily

@Composable
fun LoginScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .size(120.dp)
                .background(Color(0xFFD9D9D9))
                .clip(CircleShape),
            imageVector = Icons.Default.Person,
            contentDescription = "Logo"
        )
        Text(
            text = stringResource(R.string.enter),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = robotoFontFamily
        )

        var email by remember { mutableStateOf("") }
        InputTextFieldDefault(Modifier,
            value = email, onValueChange = { email = it }, placeHolderText = "E-mail"
        )

        var password by remember { mutableStateOf("") }
        InputTextFieldDefault(Modifier,
            value = password, onValueChange = { password = it }, placeHolderText = "Senha"
        )

    }
}


@Composable
fun InputTextFieldDefault(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
) {
    OutlinedTextField(
        modifier = modifier.padding(top = 20.dp),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color(0x80000000),
            unfocusedIndicatorColor = Color(0x80000000),
        ),
        placeholder = {
            Text(
                text = placeHolderText,
                fontSize = 15.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0x4D000000)
            )
        },
        shape = ShapeDefaults.Medium
    )
}