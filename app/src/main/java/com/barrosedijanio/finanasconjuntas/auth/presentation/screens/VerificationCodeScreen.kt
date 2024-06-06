package com.barrosedijanio.finanasconjuntas.auth.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.BtnLoginCancel
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.BtnLoginDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.InputTextFieldDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.LogoImage
import com.barrosedijanio.finanasconjuntas.auth.presentation.components.TitleTextLoginDefault
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.VerificationCodeUiState

@Composable
fun VerificationCodeScreen(
    uiState: VerificationCodeUiState,
    onVerificationCode: () -> Unit,
    onCancel: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoImage()

        TitleTextLoginDefault(text = stringResource(R.string.insert_code_sent_to_your_email))

        InputTextFieldDefault(
            value = uiState.code,
            onValueChange = { newValue ->
                uiState.onCodeChange(newValue)
            },
            placeHolderText = stringResource(R.string.code),
            error = uiState.codeState,
            leadingIcon = Icons.Default.Lock,
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        val btnEnable = uiState.codeState == InputValidResult.Valid

        BtnLoginDefault(btnEnable, stringResource(R.string.confirm)) {
            onVerificationCode()
        }

        BtnLoginCancel(onCancel)
    }
}


