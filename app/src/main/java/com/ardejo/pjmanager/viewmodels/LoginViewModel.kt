package com.ardejo.pjmanager.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }


    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val authenticationState = MutableLiveData<AuthenticationState>()

    init {
        initialize()
        refuseAuthentication()
    }

    fun authenticate() {
        if (passwordIsValidForEmail(email.value, password.value)) {
            initialize()
            acceptAuthentication()
        } else {
            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
        }
    }

    fun logout() {
        refuseAuthentication()
    }

    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    private fun acceptAuthentication() {
        authenticationState.value = AuthenticationState.AUTHENTICATED
    }

    private fun initialize() {
        email.value = ""
        password.value = ""
    }

    private fun passwordIsValidForEmail(email: String?, password: String?): Boolean {
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            return false
        }
        return true
    }
}