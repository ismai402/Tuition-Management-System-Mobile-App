package com.example.edututor.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edututor.utils.Resource

class AuthViewModel : ViewModel() {

    // Login state
    private val _loginState = MutableLiveData<Resource<User>>()
    val loginState: LiveData<Resource<User>> = _loginState

    // Register state
    private val _registerState = MutableLiveData<Resource<User>>()
    val registerState: LiveData<Resource<User>> = _registerState

    // Login function
    fun login(email: String, password: String) {
        _loginState.value = Resource.Loading()

        // Simulate login
        if (email == "tutor@example.com") {
            _loginState.value = Resource.Success(User("tutor", "Tutor Example"))
        } else {
            _loginState.value = Resource.Success(User("student", "Student Example"))
        }

        // Example error case
        // _loginState.value = Resource.Error("Login failed")
    }

    // Register function
    fun register(name: String, email: String, password: String, confirmPassword: String, role: String) {
        _registerState.value = Resource.Loading()

        // Basic validation
        if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _registerState.value = Resource.Error("All fields are required")
            return
        }

        if (password != confirmPassword) {
            _registerState.value = Resource.Error("Passwords do not match")
            return
        }

        // Simulate registration
        _registerState.value = Resource.Success(User(role, name))

        // Example error
        // _registerState.value = Resource.Error("Registration failed")
    }
}

// User data class
data class User(val role: String, val name: String)
