package com.barrosedijanio.finanasconjuntas.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.firebase.data.config.ConfigRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConfigViewModel(
    private val configRepository: ConfigRepository
) : ViewModel() {

    private val _userRemember = MutableStateFlow(false)
    val userRemember = _userRemember.asStateFlow()
    fun loggout() {
        viewModelScope.launch {
            configRepository.getUserConfig()
            FirebaseAuth.getInstance().signOut()
        }
    }
}