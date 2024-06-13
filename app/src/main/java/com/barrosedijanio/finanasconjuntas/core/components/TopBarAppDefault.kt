package com.barrosedijanio.finanasconjuntas.core.components

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarAppDefault(onProfileClick: () -> Unit, onNotificationClick: () -> Unit, photoUrl: Uri?) {

    Log.i("userPhoto", "TopBarAppDefault: $photoUrl")
    TopAppBar(
        modifier = Modifier.padding(vertical = 10.dp),
        title = {},
        navigationIcon = {
            IconButton(onClick = onProfileClick) {

                photoUrl?.let {
                    AsyncImage(
                        modifier = Modifier.size(30.dp),
                        model = it,
                        contentDescription = "Perfil"
                    )
                }?: Icon(imageVector = Icons.Default.Person, contentDescription = null)

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        actions = {
            IconButton(onClick = onNotificationClick) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "notifications"
                )
            }
        })
}