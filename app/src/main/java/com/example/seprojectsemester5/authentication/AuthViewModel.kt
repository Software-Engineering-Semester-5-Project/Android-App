package com.example.seprojectsemester5.authentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.seprojectsemester5.models.LoginResponse
import com.example.seprojectsemester5.models.MessageResponse
import com.example.seprojectsemester5.models.RegisterResponse
import com.example.seprojectsemester5.models.User
import com.example.seprojectsemester5.repositories.RemoteDataSource
import com.example.seprojectsemester5.repositories.remote.Resource
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val authRepository = AuthRepository(RemoteDataSource().buildApi(AuthApi::class.java))

    // login
    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse : LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        user : User
    ) = viewModelScope.launch {
        _loginResponse.value = authRepository.login(user)
    }

    // register
    private val _registerResponse = MutableLiveData<Resource<RegisterResponse>>()
    val registerResponse : LiveData<Resource<RegisterResponse>>
        get() = _registerResponse

    fun register(
        user : User
    ) = viewModelScope.launch {
        _registerResponse.value = authRepository.register(user)
    }

    // verify
    private val _verifyResponse = MutableLiveData<Resource<MessageResponse>>()
    val verifyResponse : LiveData<Resource<MessageResponse>>
        get() = _verifyResponse

    fun verify(
        email : String,
        otp : String
    ) = viewModelScope.launch {
        _verifyResponse.value = authRepository.verify(email, otp)
    }

    // json token storage

}