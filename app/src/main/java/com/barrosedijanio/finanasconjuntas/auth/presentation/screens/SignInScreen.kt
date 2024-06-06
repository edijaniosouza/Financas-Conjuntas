package com.barrosedijanio.finanasconjuntas.auth.presentation.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignInUiState
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    goToGoogleAuth: (context: Context) -> Unit,
    goToHome: (email: String, password: String, remember: Boolean) -> Unit,
    goToResetPassword: (email: String) -> Unit,
    goToCreateAccount: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .padding(40.dp)
                .size(120.dp)
                .clip(shape = CircleShape)
                .background(Color(0xFFD9D9D9)),
            imageVector = Icons.Default.Person,
            contentDescription = "Logo"
        )
        Text(
            text = stringResource(R.string.enter),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = robotoFontFamily
        )

        if (!uiState.error.isNullOrEmpty()){
            Text(uiState.error, fontSize = 18.sp, color = Color.Red)
        }

        InputTextFieldDefault(
            value = uiState.email,
            onValueChange = uiState.onEmailChange,
            placeHolderText = stringResource(R.string.email),
            leadingIcon = Icons.Default.Email,
            error = uiState.emailState
        )

//        var isPasswordVisibility by remember { mutableStateOf(false) }
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

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = uiState.rememberMe, onCheckedChange = uiState.onRememberChange
            )

            TextButton(onClick = {
                uiState.onRememberChange(!uiState.rememberMe)
            }) {
                Text(
                    stringResource(R.string.rememberme),
                    fontFamily = openSansFontFamily,
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }
        }
        var btnEnable = false
        if (uiState.emailState == InputValidResult.Valid && uiState.passwordState == InputValidResult.Valid) {
            btnEnable = true
        }

        Button(
            onClick = {
                goToHome(uiState.email, uiState.password, uiState.rememberMe)
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




