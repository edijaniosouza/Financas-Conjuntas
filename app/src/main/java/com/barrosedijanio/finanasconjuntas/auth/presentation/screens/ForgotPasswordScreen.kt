package com.barrosedijanio.finanasconjuntas.auth.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.BtnLoginCancel
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.BtnLoginDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.LogoImage
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.TitleTextLoginDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.ForgotPasswordUiState

@Composable
fun ForgotPasswordScreen(
    uiState: ForgotPasswordUiState,
    onResetPassword: () -> Unit,
    onCancel: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoImage()
        TitleTextLoginDefault(text = stringResource(R.string.insert_your_email_to_reset_password))
        InputTextFieldDefault(
            value = uiState.email,
            onValueChange = { newValue ->
                uiState.onEmailChange(newValue)
            },
            placeHolderText = stringResource(R.string.email),
            error = uiState.emailState,
            leadingIcon = Icons.Default.Email
        )

        BtnLoginDefault(
            enable = uiState.emailState == InputValidResult.Valid,
            stringResource(R.string.reset_password)
        ) {
            onResetPassword()
        }

        BtnLoginCancel(onCancel)
    }
}


