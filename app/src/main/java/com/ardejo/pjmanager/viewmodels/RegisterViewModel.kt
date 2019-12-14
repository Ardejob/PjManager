package com.ardejo.pjmanager.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardejo.pjmanager.models.User

class RegisterViewModel : ViewModel() {

    enum class RegistrationState {
        COLLECT_PROFILE_DATA,
        REGISTRATION_COMPLETED,
        REGISTRATION_FAILED
    }

    val userLiveData = MutableLiveData<User>()
    val passwordLiveData = MutableLiveData<String>()

    val registrationState = MutableLiveData<RegistrationState>()

    init {
        registrationState.value = RegistrationState.COLLECT_PROFILE_DATA
        initialize()
    }

    fun createAccountAndLogin() {
        // TODO: Create an account
        // TODO: Authenticate

        initialize()
        registrationState.value = RegistrationState.REGISTRATION_COMPLETED
    }

    fun userCancelledRegistration() {
        // Clear existing registration data
        registrationState.value = RegistrationState.COLLECT_PROFILE_DATA
    }

    private fun initialize() {
        userLiveData.value = User()
        passwordLiveData.value = ""
    }

}