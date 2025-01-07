package com.example.tiffinapp.feature.login.UI


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiffin.core.data.UserProfile
import com.example.tiffin.core.domain.AuthRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val result = authRepository.loginUser(
                    email = email,
                    password = password
                )
                if (result.isSuccess) {
                    val userProfile = result.getOrNull()!!
                    _loginState.value = LoginState.Success(userProfile)
                } else {
                    _loginState.value = LoginState.Error("Login failed: ${result.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error: ${e.message}")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val userProfile: UserProfile) : LoginState()
    data class Error(val errorMessage: String) : LoginState()
}
