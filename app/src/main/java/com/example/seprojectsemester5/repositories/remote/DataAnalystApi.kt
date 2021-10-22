package com.example.seprojectsemester5.repositories.remote

import com.example.seprojectsemester5.models.DataAnalystGetDataSummary
import com.example.seprojectsemester5.models.MessageResponse
import retrofit2.http.*

interface DataAnalystApi {

    @GET("summary/update/{pin_code}")
    suspend fun updateSummaryData(
        @Header(value = "x-auth-token") authToken : String,
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