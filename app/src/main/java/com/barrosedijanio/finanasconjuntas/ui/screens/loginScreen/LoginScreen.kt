package com.barrosedijanio.finanasconjuntas.ui.screens.loginScreen

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.ui.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid

@Composable
fun LoginScreen(
    emailStateValidation: (email: String) -> LoginInputValid,
    passwordStateValidation: (password: String) -> LoginInputValid,
    goToGoogleAuth: () -> Unit,
    goToHome: (email: String, password: String, remember: Boolean) -> Unit,
    goToResetPassword: (email: String) -> Unit,
    goToCreateAccount: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var emailState by rememberSaveable { mutableStateOf<LoginInputValid>(LoginInputValid.Empty) }
        var passwordState by rememberSaveable { mutableStateOf<LoginInputValid>(LoginInputValid.Empty) }
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var rememberMe by rememberSaveable { mutableStateOf(false) }

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

        InputTextFieldDefault(
            value = email,
            onValueChange = { newValue ->
                email = newValue
                emailState = emailStateValidation(email)
            },
            placeHolderText = stringResource(R.string.email),
            error = emailState
        )

        InputTextFieldDefault(
            value = password,
            onValueChange = { newValue ->
                password = newValue
                passwordState = passwordStateValidation(password)
            },
            placeHolderText = stringResource(R.string.password),
            error = passwordState
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })

            TextButton(onClick = {
                rememberMe = !rememberMe
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
        if(emailState == LoginInputValid.Valid && passwordState == LoginInputValid.Valid){
            btnEnable = true
        }

        Button(
            onClick = {
                goToHome(email, password, rememberMe)
            },
            Modifier
                .fillMaxWidth(),
            shape = ShapeDefaults.Medium,
            border = if(btnEnable) BorderStroke(width = 1.dp, color = Color(0xFF407A66)) else null,
            enabled = btnEnable
        ) {
            Text(stringResource(id = R.string.enter))
        }

        val fontSize = 10.sp
        val fontWeight = FontWeight.SemiBold
        val fontFamily = robotoFontFamily
        TextButton(
            onClick = { goToResetPassword(email) }) {
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
                .padding(vertical = 40.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
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
            Divider(
                modifier = Modifier
                    .width(144.dp)
                    .padding(horizontal = 10.dp),
                color = Color(0x4D000000)
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = goToGoogleAuth,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(15.dp),
            shape = ShapeDefaults.ExtraLarge,
            border = BorderStroke(width = 1.dp, color = Color(0xFF8D8D8D))
        ) {

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


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        { LoginInputValid.Empty },
        { LoginInputValid.Empty },
        {},
        goToHome = { _, _, _ -> },
        {},
        {})
}
