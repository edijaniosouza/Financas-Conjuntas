package com.barrosedijanio.finanasconjuntas
import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignInViewModel
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SignInViewModelTest {

    lateinit var repository : AuthRepositoryImpl
    lateinit var viewModel: SignInViewModel

    @Before
    fun setup(){
        val firebaseAuth = FirebaseAuth.getInstance()
        repository = AuthRepositoryImpl(firebaseAuth)
        viewModel = SignInViewModel(repository)
    }
    @Test
    fun emailStateValidation_emptyEmail_returnsError() {
        val email = ""
        val expectedResult = InputValidResult.Error(R.string.empty_field)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expectedResult, result)
    }

    @Test
    fun emailStateValidation_validEmail_returnsValid() {
        val email = "test@test.com"
        val expectedResult = InputValidResult.Valid
        val result = viewModel.emailStateValidation(email)
        assertEquals(expectedResult, result)
    }

    @Test
    fun emailStateValidation_invalidEmail_returnsError() {
        val email = "test"
        val expectedResult = InputValidResult.Error(R.string.invalid_email)
        val result = viewModel.emailStateValidation(email)
        assertEquals(expectedResult, result)
    }

    @Test
    fun passwordStateValidation_emptyPassword_returnsError() {
        val password = ""
        val expectedResult = InputValidResult.Error(R.string.empty_field)
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expectedResult, result)
    }

    @Test
    fun passwordStateValidation_validPassword_returnsValid() {
        val password = "Test1234"
        val expectedResult = InputValidResult.Valid
        val result = viewModel.passwordStateValidation(password)
        assertEquals(expectedResult, result)
    }
}