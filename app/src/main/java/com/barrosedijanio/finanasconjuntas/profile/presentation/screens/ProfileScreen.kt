package com.barrosedijanio.finanasconjuntas.profile.presentation.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profilePhoto: Uri,
    onSignOutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            Modifier.padding(vertical = 50.dp)
        ) {

            if (profilePhoto != Uri.EMPTY) {
                AsyncImage(
                    modifier = Modifier
                        .size(156.dp)
                        .clip(CircleShape),
                    model = profilePhoto,
                    contentDescription = "foto de perfil"
                )
            } else {
                Icon(modifier = Modifier
                    .size(156.dp)
                    .clip(CircleShape),
                    imageVector = Icons.Default.Person,
                    contentDescription = null)
            }

            IconButton(
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
                    .align(Alignment.BottomEnd),
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "botão editar foto de perfil",
                    tint = Color.Black
                )
            }
        }

        Card(
            modifier = Modifier
                .height(150.dp)
                .padding(bottom = 50.dp, end = 30.dp, start = 30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 5.dp
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = ShapeDefaults.ExtraLarge,
            onClick = { /*TODO*/ }) {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Compartilhamento ativado",
                        fontSize = 14.sp,
                        fontFamily = openSansFontFamily
                    )
                    Text(
                        "Nome da Pessoa compartilhada",
                        fontSize = 14.sp,
                        fontFamily = openSansFontFamily
                    )
                }
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto de perfil"
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    "Perfil",
                    fontSize = 24.sp,
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
            TextButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    "Configurações",
                    fontSize = 24.sp,
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.padding(15.dp))
            TextButton(
                onClick = { onSignOutClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    "Sair",
                    fontSize = 14.sp,
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}