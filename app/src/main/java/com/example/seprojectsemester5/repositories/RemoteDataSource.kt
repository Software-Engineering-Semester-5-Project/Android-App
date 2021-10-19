package com.example.seprojectsemester5.repositories

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RemoteDataSource {
    companion object{
        private const val BASE_URL = "https://g0uravlathwal.herokuapp.com/"
    }

    fun <Api> buildApi(
        api : Class<Api>
    ) : Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(api)
}