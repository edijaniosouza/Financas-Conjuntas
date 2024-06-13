package com.barrosedijanio.finanasconjuntas.profile.presentation.navigation

import android.net.Uri
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

        ProfileScreen(profilePhoto = profilePhoto,onSignOutClick)
    }
}