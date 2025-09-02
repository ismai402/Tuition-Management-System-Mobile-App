package com.example.edututor.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edututor.data.local.entities.UserEntity
import com.example.edututor.di.AppModule
import com.example.edututor.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepo = AppModule.users()
    private val _loginState = MutableLiveData<Resource<UserEntity>>()
    val loginState: LiveData<Resource<UserEntity>> = _loginState

    private val _registerState = MutableLiveData<Resource<UserEntity>>()
    val registerState: LiveData<Resource<UserEntity>> = _registerState

    fun login(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepo.login(email, password).collect { _loginState.postValue(it) }
        }
    }

    fun register(name: String, email: String, password: String, role: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepo.register(name, email, password, role).collect { _registerState.postValue(it) }
        }
    }
}
