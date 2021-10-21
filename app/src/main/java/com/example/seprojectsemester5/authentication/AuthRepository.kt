package com.example.seprojectsemester5.authentication

import com.example.seprojectsemester5.models.User
import com.example.seprojectsemester5.repositories.BaseRepository

class AuthRepository(private val api : AuthApi) : BaseRepository() {

    suspend fun login(
        user : User
    ) = safeApiCall {
        api.login(user.email, user.password, user.role)
    }

    suspend fun register(
        user : User
    ) = safeApiCall {
        api.register(user.name!!, user.email, user.password, user.role)
    }

    suspend fun verify(
        email : String,
        otp : String
    ) = safeApiCall {
        api.verify(email, otp)
    }
}