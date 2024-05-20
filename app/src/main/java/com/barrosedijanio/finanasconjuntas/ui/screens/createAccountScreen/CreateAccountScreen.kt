package com.barrosedijanio.finanasconjuntas.ui.screens.createAccountScreen

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.ui.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid

@Composable
fun CreateAccountScreen(
    nameStateValidation: (name: String) -> LoginInputValid,
    emailStateValidation: (email: String) -> LoginInputValid,
    passwordStateValidation: (password: String) -> LoginInputValid,
    confirmPasswordStateValidation: (password: String, confirmPassword: String) -> LoginInputValid,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        var emailState by rememberSaveable { mutableStateOf<LoginInputValid>(LoginInputValid.Empty) }
        var passwordState by rememberSaveable { mutableStateOf<LoginInputValid>(LoginInputValid.Empty) }
        var nameState by rememberSaveable { mutableStateOf<LoginInputValid>(LoginInputValid.Empty) }
        var confirmPasswordState by rememberSaveable {
            mutableStateOf<LoginInputValid>(
                LoginInputValid.Empty
            )
        }
        var email by rememberSaveable { mutableStateOf("") }
        var name by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        var termsAgree by rememberSaveable { mutableStateOf(false) }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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

            InputTextFieldDefault(
                value = name,
                onValueChange = { newValue ->
                    name = newValue
                    nameState = nameStateValidation(name)
                },
                placeHolderText = stringResource(R.string.name),
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

            InputTextFieldDefault(
                value = confirmPassword,
                onValueChange = { newValue ->
                    confirmPassword = newValue
                    confirmPasswordState = confirmPasswordStateValidation(password, confirmPassword)
                },
                placeHolderText = stringResource(R.string.confirmPassword),
                error = confirmPasswordState
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
                Checkbox(checked = termsAgree, onCheckedChange = { termsAgree = it })

                TextButton(onClick = {
                    termsAgree = !termsAgree
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
            if (emailState == LoginInputValid.Valid &&
                confirmPasswordState == LoginInputValid.Valid &&
                passwordState == LoginInputValid.Valid &&
                termsAgree
                ) {
                btnEnable = true
            }

            Button(
                onClick = {
                          /*TODO: IMPLEMENTAR NAVEGAÇÃO*/
                },
                Modifier
                    .fillMaxWidth(),
                shape = ShapeDefaults.Medium,
                border = if (btnEnable) BorderStroke(width = 1.dp, color = Color(0xFF407A66)) else null,
                enabled = btnEnable
            ) {
                Text(stringResource(id = R.string.signUp))
            }
        }


    }
}
