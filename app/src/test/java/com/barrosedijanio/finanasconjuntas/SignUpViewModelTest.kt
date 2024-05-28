package com.barrosedijanio.finanasconjuntas

import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignUpViewModel
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.google.firebase.auth.FirebaseAuth
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class SignUpViewModelTest {
    private lateinit var repository : AuthRepositoryImpl
    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setup(){
        val firebaseAuth = FirebaseAuth.getInstance()
        repository = AuthRepositoryImpl(firebaseAuth)
        viewModel = SignUpViewModel(repository)
    }

    @Test
    fun emailStateValidation_emptyEmail_returnsError(){
        val email = ""
        val expected = InputValidResult.Error(R.string.empty_field)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expected, result)
    }

    @Test
    fun emailStateValidation_wrongEmail_returnsError(){
        val email = "abc@abc"
        val expected = InputValidResult.Error(R.string.invalid_email)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expected, result)
    }

    @Test
    fun emailStateValidation_correctEmail_returnsValid(){
        val email = "abc@gmail.com"
        val expect = InputValidResult.Valid
        val result = viewModel.emailStateValidation(email)
        assertEquals(expect, result)
    }

    @Test
    fun passwordStateValidation_passwordLessThatSixDigits_returnsError(){
        val password = "12345"
        val expected = InputValidResult.Error(R.string.password_short)
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expected, result)
    }

    @Test
    fun passwordStateValidation_emptyPassword_returnsError(){
        val password = ""
        val expected = InputValidResult.Error(R.string.empty_field)
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expected, result)
    }

    @Test
    fun passwordStateValidation_correctPassword_returnsValid(){
        val password = "123456"
        val expected = InputValidResult.Valid
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expected, result)
    }

    @Test
    fun confirmPasswordStateValidation_differentPasswords_returnsError(){
        val password = "123456"
        val confirmPassword = "123458"
        val expected = InputValidResult.Error(R.string.password_not_equal)
        val result = viewModel.confirmPasswordStateValidation(password, confirmPassword)
        assertEquals(expected, result)
    }

    @Test
    fun confirmPasswordStateValidation_emptyConfirmPassword_returnsError(){
        val password = "123456"
        val confirmPassword = ""
        val expected = InputValidResult.Error(R.string.empty_field)
        val result = viewModel.confirmPasswordStateValidation(password, confirmPassword)
        assertEquals(expected, result)
    }
    @Test
    fun confirmPasswordStateValidation_correctPassword_returnsValid(){
        val password = "123456"
        val confirmPassword = "123456"
        val expected = InputValidResult.Valid
        val result = viewModel.confirmPasswordStateValidation(password, confirmPassword)
        assertEquals(expected, result)
    }

    @Test
    fun nameStateValidation_tooShort_returnsError(){
        val name = "abc"
        val expected = InputValidResult.Error(R.string.nameTooShort)
        val result = viewModel.nameStateValidation(name)
        assertEquals(expected, result)
    }

    @Test
    fun nameStateValidation_empty_returnsError(){
        val name = ""
        val expected = InputValidResult.Error(R.string.empty_field)
        val result = viewModel.nameStateValidation(name)
        assertEquals(expected, result)
    }

    @Test
    fun nameStateValidation_ok_returnsValid(){
        val name = "abcd"
        val expected = InputValidResult.Valid
        val result = viewModel.nameStateValidation(name)
        assertEquals(expected, result)
    }

}
