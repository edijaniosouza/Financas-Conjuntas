package com.barrosedijanio.finanasconjuntas.auth.presentation.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.model.AuthResult
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.LogoImage
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignInUiState
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    authState: AuthResult,
    goToGoogleAuth: (context: Context) -> Unit,
    onLogin: (email: String, password: String, remember: Boolean) -> Unit,
    goToHomeScreen: () -> Unit,
    goToResetPassword: (email: String) -> Unit,
    goToCreateAccount: () -> Unit
) {
    LaunchedEffect(key1 = authState) {
        when (authState) {
            is AuthResult.OK -> {
                uiState.onLoadingChange(false)
                uiState.onErrorChange("")
                goToHomeScreen()
            }
            is AuthResult.Error -> {
                uiState.onLoadingChange(false)
                uiState.onErrorChange(authState.errorMessage)
            }
            is AuthResult.Loading -> {
                uiState.onLoadingChange(true)
            }
            is AuthResult.Empty -> {
                uiState.onErrorChange("")
                uiState.onLoadingChange(false)
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LogoImage()
        Text(
            text = stringResource(R.string.enter),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = robotoFontFamily
        )

        InputTextFieldDefault(
            value = uiState.email,
            onValueChange = uiState.onEmailChange,
            placeHolderText = stringResource(R.string.email),
            leadingIcon = Icons.Default.Email,
            error = uiState.emailState
        )

        InputTextFieldDefault(
            value = uiState.password,
            onValueChange = uiState.onPasswordChange,
            placeHolderText = stringResource(R.string.password),
            leadingIcon = Icons.Default.Lock,
            trailingIcon = if (uiState.isPasswordVisible) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(
                id = R.drawable.baseline_visibility_off_24
            ),
            trailingIconClick = { uiState.onPasswordVisibleChange(!uiState.isPasswordVisible) },
            visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            error = uiState.passwordState
        )

//        Row(
//            Modifier
//                .fillMaxWidth()
//                .padding(bottom = 15.dp),
//            horizontalArrangement = Arrangement.Start,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Checkbox(
//                checked = uiState.rememberMe, onCheckedChange = uiState.onRememberChange
//            )
//
//            TextButton(onClick = {
//                uiState.onRememberChange(!uiState.rememberMe)
//            }) {
//                Text(
//                    stringResource(R.string.rememberme),
//                    fontFamily = openSansFontFamily,
//                    fontSize = 15.sp,
//                    color = Color.Black
//                )
//            }
//        }
        var btnEnable = false
        if (uiState.emailState == InputValidResult.Valid && uiState.passwordState == InputValidResult.Valid) {
            btnEnable = true
        }

        if(!uiState.error.isNullOrEmpty()){
            Text(uiState.error, fontSize = 18.sp, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                onLogin(uiState.email, uiState.password, uiState.rememberMe)
            },
            Modifier
                .fillMaxWidth(),
            shape = ShapeDefaults.Medium,
            border = if (btnEnable) BorderStroke(width = 1.dp, color = Color(0xFF407A66)) else null,
            enabled = btnEnable
        ) {
            Text(stringResource(id = R.string.enter))
        }

        val fontSize = 10.sp
        val fontWeight = FontWeight.SemiBold
        val fontFamily = robotoFontFamily
        TextButton(
            onClick = { goToResetPassword(uiState.email) }) {
            Text(
                text = stringResource(R.string.if_with_problems_reset_password),
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Sem conta?",
                fontSize = fontSize,
                fontWeight = fontWeight,
                fontFamily = fontFamily
            )
            TextButton(onClick = goToCreateAccount) {
                Text(
                    " Crie sua conta",
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .width(144.dp)
                    .padding(horizontal = 10.dp),
                color = Color(0x4D000000)
            )
            Text(
                "OU",
                fontFamily = robotoFontFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(
                modifier = Modifier
                    .width(144.dp)
                    .padding(horizontal = 10.dp),
                color = Color(0x4D000000)
            )
        }

        val ctx = LocalContext.current
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { goToGoogleAuth(ctx) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(15.dp),
            shape = ShapeDefaults.ExtraLarge,
            border = BorderStroke(width = 1.dp, color = Color(0xFF8D8D8D))
        ) {

            if(uiState.isLoading){
                CircularProgressIndicator()
            } else{
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google Icon",
                        Modifier
                            .padding()
                            .size(30.dp)
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 40.dp),
                        text = "Entrar com Google",
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }

        }
    }
}






