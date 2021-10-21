package com.example.seprojectsemester5.authentication

import com.example.seprojectsemester5.models.LoginResponse
import com.example.seprojectsemester5.models.MessageResponse
import com.example.seprojectsemester5.models.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    /**
     * role: Analyst / Collector (use same spellings)
     */

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("role") role : String
    ) : LoginResponse

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("role") role: String,
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("user/verify")
    suspend fun verify(
        @Field("email") email: String,
        @Field("otp") otp : String,
    ) : MessageResponse
}