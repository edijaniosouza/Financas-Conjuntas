package com.barrosedijanio.finanasconjuntas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.barrosedijanio.finanasconjuntas.core.navigation.Navigation
import com.barrosedijanio.finanasconjuntas.core.viewmodel.ConfigViewModel
import com.barrosedijanio.finanasconjuntas.firebase.data.config.ConfigRepository
import com.barrosedijanio.finanasconjuntas.firebase.data.config.USER_CONFIG
import com.barrosedijanio.finanasconjuntas.ui.theme.FinançasConjuntasTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    lateinit var viewmodel : ConfigViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            viewmodel = koinViewModel<ConfigViewModel>()

            FinançasConjuntasTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }

            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinançasConjuntasTheme {
        Navigation()
    }
}