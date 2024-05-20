package com.barrosedijanio.finanasconjuntas

import com.barrosedijanio.finanasconjuntas.ui.screens.createAccountScreen.CreateAccountScreenViewModel
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CreateAccountScreenViewModelTest {
    private var viewModel: CreateAccountScreenViewModel = CreateAccountScreenViewModel()

    @Test
    fun emailStateValidation_emptyEmail_returnsError(){
        val email = ""
        val expected = LoginInputValid.Error(R.string.empty_field)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expected, result)
    }

    @Test
    fun emailStateValidation_wrongEmail_returnsError(){
        val email = "abc@abc"
        val expected = LoginInputValid.Error(R.string.invalid_email)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expected, result)
    }

    @Test
    fun emailStateValidation_correctEmail_returnsValid(){
        val email = "abc@gmail.com"
        val expect = LoginInputValid.Valid
        val result = viewModel.emailStateValidation(email)
        assertEquals(expect, result)
    }

    @Test
    fun passwordStateValidation_passwordLessThatSixDigits_returnsError(){
        val password = "12345"
        val expected = LoginInputValid.Error(R.string.password_short)
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expected, result)
    }

    @Test
    fun passwordStateValidation_emptyPassword_returnsError(){
        val password = ""
        val expected = LoginInputValid.Error(R.string.empty_field)
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expected, result)
    }

    @Test
    fun passwordStateValidation_correctPassword_returnsValid(){
        val password = "123456"
        val expected = LoginInputValid.Valid
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expected, result)
    }

    @Test
    fun confirmPasswordStateValidation_differentPasswords_returnsError(){
        val password = "123456"
        val confirmPassword = "123458"
        val expected = LoginInputValid.Error(R.string.password_not_equal)
        val result = viewModel.confirmPasswordStateValidation(password, confirmPassword)
        assertEquals(expected, result)
    }

    @Test
    fun confirmPasswordStateValidation_emptyConfirmPassword_returnsError(){
        val password = "123456"
        val confirmPassword = ""
        val expected = LoginInputValid.Error(R.string.empty_field)
        val result = viewModel.confirmPasswordStateValidation(password, confirmPassword)
        assertEquals(expected, result)
    }
    @Test
    fun confirmPasswordStateValidation_correctPassword_returnsValid(){
        val password = "123456"
        val confirmPassword = "123456"
        val expected = LoginInputValid.Valid
        val result = viewModel.confirmPasswordStateValidation(password, confirmPassword)
        assertEquals(expected, result)
    }

    @Test
    fun nameStateValidation_tooShort_returnsError(){
        val name = "abc"
        val expected = LoginInputValid.Error(R.string.nameTooShort)
        val result = viewModel.nameStateValidation(name)
        assertEquals(expected, result)
    }

    @Test
    fun nameStateValidation_empty_returnsError(){
        val name = ""
        val expected = LoginInputValid.Error(R.string.empty_field)
        val result = viewModel.nameStateValidation(name)
        assertEquals(expected, result)
    }

    @Test
    fun nameStateValidation_ok_returnsValid(){
        val name = "abcd"
        val expected = LoginInputValid.Valid
        val result = viewModel.nameStateValidation(name)
        assertEquals(expected, result)
    }

}
