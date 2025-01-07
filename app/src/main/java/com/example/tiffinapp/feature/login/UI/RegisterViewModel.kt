package com.example.tiffinapp.feature.login.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiffin.core.data.UserProfile
import com.example.tiffin.core.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun registerUser(name: String, email: String, password: String, phoneNumber: String, dateOfBirth: String?) {
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            val result = authRepository.registerUser(name, email, password, phoneNumber, dateOfBirth)
            _uiState.value = if (result.isSuccess) {
                RegisterUiState.Success(result.getOrNull()!!)
            } else {
                RegisterUiState.Error(result.exceptionOrNull()?.localizedMessage ?: "Unknown error")
            }
        }
    }
}

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val userProfile: UserProfile) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}