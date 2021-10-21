package com.example.seprojectsemester5.repositories

import com.example.seprojectsemester5.models.DiseaseFilters
import com.example.seprojectsemester5.repositories.remote.DataAnalystApi

class DataAnalystRepository(private val api : DataAnalystApi) : BaseRepository() {

    suspend fun updateSummaryData(
        pinCode : Int
    ) = safeApiCall {
        api.updateSummaryData(pinCode)
    }

    suspend fun getSummaryData(
        diseaseFilters: DiseaseFilters
    ) = safeApiCall {
        api.getSummaryData(
            startDate = diseaseFilters.startDate,
            endDate = diseaseFilters.endDate,
            pinCode = diseaseFilters.pin,
            gender = diseaseFilters.sex,
            diseaseType = diseaseFilters.diseaseType
        )
    }
}