package com.example.seprojectsemester5.repositories.remote

import com.example.seprojectsemester5.models.DataAnalystGetDataSummary
import com.example.seprojectsemester5.models.MessageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface DataAnalystApi {

    @GET("summary/update/{pin_code}")
    suspend fun updateSummaryData(
        @Path (value = "pin_code") pinCode : Int
    ) : MessageResponse

    @GET("summary/get/{startDate}/{endDate}/{pinCode}/{gender}/{disease_type}")
    suspend fun getSummaryData(
        @Path (value = "startDate") startDate : Int,
        @Path (value = "endDate") endDate : Int,
        @Path (value = "pinCode") pinCode : Int,
        @Path (value = "gender") gender : String,
        @Path (value = "disease_type") diseaseType : String
    ) : DataAnalystGetDataSummary
}