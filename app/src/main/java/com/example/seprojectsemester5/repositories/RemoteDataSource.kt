package com.example.seprojectsemester5.repositories

import com.example.seprojectsemester5.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

class RemoteDataSource {
    companion object{
        private const val BASE_URL = "https://g0uravlathwal.herokuapp.com/"
    }

    fun <Api> buildApi(
        api : Class<Api>
    ) : Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder().also { client ->
                if(BuildConfig.DEBUG){
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
                    client.addInterceptor(logging)
                }
            }.build()
        )
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(api)
}