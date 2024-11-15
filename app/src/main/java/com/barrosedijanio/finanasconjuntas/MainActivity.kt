package com.barrosedijanio.finanasconjuntas

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.barrosedijanio.finanasconjuntas.core.navigation.Navigation
import com.barrosedijanio.finanasconjuntas.core.viewmodel.ConfigViewModel
import com.barrosedijanio.finanasconjuntas.ui.theme.FinançasConjuntasTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    lateinit var viewmodel: ConfigViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var showDialog = false
        // teste link
        val data: Uri? = intent?.data
        data?.let {
            val sharedId = it.getQueryParameter("id")
            showDialog = true
            Toast.makeText(this, "ID -> $sharedId", Toast.LENGTH_SHORT).show()
        }

        setContent {
            viewmodel = koinViewModel<ConfigViewModel>()
            FinançasConjuntasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(showDialog)
                }
            }
        }
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FinançasConjuntasTheme {
//        Navigation()
//    }
//}