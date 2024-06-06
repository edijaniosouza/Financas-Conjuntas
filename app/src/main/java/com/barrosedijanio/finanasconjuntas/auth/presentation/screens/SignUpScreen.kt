package com.barrosedijanio.finanasconjuntas.auth.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.LogoImage
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignUpUiState
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onSignUp: (email: String, password: String, username: String) -> Unit,
    onSignUpResult: Result,
    navigateToHomeScreen: (username: String) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        LaunchedEffect(key1 = onSignUpResult) {
            when (onSignUpResult) {
                is Result.Loading -> {
                    uiState.onLoadingChange(true)
                }

                is Result.OK -> {
                    uiState.onLoadingChange(false)
                    navigateToHomeScreen(uiState.userName)
                }

                else -> {
                    uiState.onLoadingChange(false)
                    Log.e("SignUpScreen", "SignUpScreen: Erro ao cadastrar")
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoImage()
            Text(
                text = stringResource(R.string.enter),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                fontFamily = robotoFontFamily
            )

            InputTextFieldDefault(
                value = uiState.userName,
                onValueChange = { newValue ->
                    uiState.onUserNamechange(newValue)
                },
                placeHolderText = stringResource(R.string.name),
                leadingIcon = Icons.Default.Face,
                error = uiState.userNameState
            )

            InputTextFieldDefault(
                value = uiState.email,
                onValueChange = { newValue ->
                    uiState.onEmailChange(newValue)
                },
                placeHolderText = stringResource(R.string.email),
                error = uiState.emailState,
                leadingIcon = Icons.Default.Email
            )

            var isPasswordVisibility by remember { mutableStateOf(false) }
            InputTextFieldDefault(
                value = uiState.password,
                onValueChange = { newValue ->
                    uiState.onPasswordChange(newValue)
                },
                placeHolderText = stringResource(R.string.password),
                leadingIcon = Icons.Default.Lock,
                trailingIcon = if (isPasswordVisibility) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(
                    id = R.drawable.baseline_visibility_off_24
                ),
                trailingIconClick = {
                    isPasswordVisibility = !isPasswordVisibility
                },
                visualTransformation = if (isPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                error = uiState.passwordState
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
                    isPasswordVisibility = !isPasswordVisibility
                },
                trailingIcon = if (isPasswordVisibility) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(
                    id = R.drawable.baseline_visibility_off_24
                ),
                visualTransformation = if (isPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            )
        }


        Column(
            Modifier.padding(bottom = 30.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = uiState.termsAgree,
                    onCheckedChange = {isChecked ->
                        uiState.onTermsChange(isChecked)
                    })

                TextButton(onClick = {
                    uiState.onTermsChange(!uiState.termsAgree)
                }) {
                    Text(
                        stringResource(R.string.agree_with_terms),
                        fontFamily = openSansFontFamily,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                }
            }

            var btnEnable = false
            if (uiState.emailState == InputValidResult.Valid &&
                uiState.confirmPasswordState == InputValidResult.Valid &&
                uiState.passwordState == InputValidResult.Valid &&
                uiState.termsAgree
            ) {
                btnEnable = true
            }

            Button(
                onClick = {
                    onSignUp(uiState.email, uiState.confirmPassword, uiState.userName)
                },
                Modifier
                    .fillMaxWidth(),
                shape = ShapeDefaults.Medium,
                border = if (btnEnable) BorderStroke(
                    width = 1.dp,
                    color = Color(0xFF407A66)
                ) else null,
                enabled = btnEnable
            ) {
                if (uiState.isLoading) CircularProgressIndicator()
                else Text(stringResource(id = R.string.signUp))
            }
        }
    }
}
