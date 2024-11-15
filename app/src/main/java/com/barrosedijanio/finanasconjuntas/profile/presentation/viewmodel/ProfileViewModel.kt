package com.barrosedijanio.finanasconjuntas.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.data.accountLink.AccountLinkRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val linkRepository: AccountLinkRepository
) : ViewModel() {


    fun setupLink(onSend: (senderId: String) -> Unit) {
        viewModelScope.launch {
            linkRepository.sendRequest().collect{ it ->
                when(it){
                    Result.Empty -> TODO()
                    is Result.Error -> TODO()
                    Result.Loading -> TODO()
                    is Result.OK -> {
                        onSend(it.value!!)
                    }
                }
            }
        }
    }
}