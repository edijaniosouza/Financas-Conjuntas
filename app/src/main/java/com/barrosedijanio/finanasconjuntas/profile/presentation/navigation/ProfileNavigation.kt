package com.barrosedijanio.finanasconjuntas.profile.presentation.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.profile.presentation.screens.ProfileScreen
import com.barrosedijanio.finanasconjuntas.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.profileScreen(
    profilePhoto: Uri,
    onSignOutClick: () -> Unit,
) {
    composable(Screens.Profile.route) {
        val viewModel: ProfileViewModel = koinViewModel()

        val context = LocalContext.current
        ProfileScreen(profilePhoto = profilePhoto, {
            viewModel.setupLink { userId ->
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "https://www.psiamandatheodoro.com/share?id=$userId")
                    type = "text/plain"
                }
                startActivity(context, shareIntent, null)
            }
        },onSignOutClick)
    }
}