package com.barrosedijanio.finanasconjuntas
import com.barrosedijanio.finanasconjuntas.ui.screens.loginScreen.LoginScreenViewModel
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginScreenViewModelTest {

    private var viewModel: LoginScreenViewModel = LoginScreenViewModel()

    @Test
    fun emailStateValidation_emptyEmail_returnsError() {
        val email = ""
        val expectedResult = LoginInputValid.Error(R.string.empty_field)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expectedResult, result)
    }

    @Test
    fun emailStateValidation_validEmail_returnsValid() {
        val email = "test@test.com"
        val expectedResult = LoginInputValid.Valid
        val result = viewModel.emailStateValidation(email)
        assertEquals(expectedResult, result)
    }

    @Test
    fun emailStateValidation_invalidEmail_returnsError() {
        val email = "test"
        val expectedResult = LoginInputValid.Error(R.string.invalid_email)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expectedResult, result)
    }

    @Test
    fun passwordStateValidation_emptyPassword_returnsError() {
        val password = ""
        val expectedResult = LoginInputValid.Error(R.string.empty_field)
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expectedResult, result)
    }

    @Test
    fun passwordStateValidation_validPassword_returnsValid() {
        val password = "Test1234"
        val expectedResult = LoginInputValid.Valid
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expectedResult, result)
    }
}