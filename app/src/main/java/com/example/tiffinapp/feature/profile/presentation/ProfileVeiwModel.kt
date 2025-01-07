package com.example.tiffin.feature.profile.presentation
/*
// feature/profile/presentation/ProfileViewModel.kt
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> get() = _userProfile

    fun loadUserProfile() {
        viewModelScope.launch {
            val userId = authRepository.getCurrentUserId() ?: return@launch
            // TODO: Load profile data from repository
        }
    }
}
*/