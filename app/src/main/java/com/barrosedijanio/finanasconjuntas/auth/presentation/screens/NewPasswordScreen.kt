package com.barrosedijanio.finanasconjuntas.auth.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.BtnLoginCancel
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.BtnLoginDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.LogoImage
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.TitleTextLoginDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.NewPasswordUiState

@Composable
fun NewPasswordScreen(
    uiState: NewPasswordUiState,
    onNewPassword: () -> Unit,
    onCancel: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoImage()
        TitleTextLoginDefault(text = stringResource(R.string.new_password))

        InputTextFieldDefault(
            value = uiState.password,
            onValueChange = { newValue ->
                uiState.onPasswordChange(newValue)
            },
            placeHolderText = stringResource(R.string.confirmPassword),
            error = uiState.passwordState,
            leadingIcon = Icons.Default.Lock,
            trailingIconClick = {
                uiState.onPasswordVisibleChange(!uiState.isPasswordVisible)
            },
            trailingIcon = if (uiState.isPasswordVisible) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(
                id = R.drawable.baseline_visibility_off_24
            ),
            visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )

        InputTextFieldDefault(
            value = uiState.confirmPassword,
            onValueChange = { newValue ->
                uiState.onConfirmPasswordChange(newValue)
            },
            placeHolderText = stringResource(R.string.confirmPassword),
            error = uiState.confirmPasswordState,
            leadingIcon = Icons.Default.Lock,
            trailingIconClick = {
                uiState.onConfirmPasswordVisibleChange(!uiState.isConfirmPasswordVisible)
            },
            trailingIcon = if (uiState.isConfirmPasswordVisible) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(
                id = R.drawable.baseline_visibility_off_24
            ),
            visualTransformation = if (uiState.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )

        val btnEnable =
            uiState.passwordState == InputValidResult.Valid && uiState.confirmPasswordState == InputValidResult.Valid

        BtnLoginDefault(btnEnable, stringResource(R.string.confirm)) {
            onNewPassword()
        }

        BtnLoginCancel(onCancel)
    }
}


