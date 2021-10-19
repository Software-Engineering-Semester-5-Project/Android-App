package com.example.seprojectsemester5.repositories.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    /**
     * role: Analyst / DataCollector (use same spellings)
     */

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Any

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String
    ) : Any
}