package com.example.seprojectsemester5.dataanalyst

import android.app.Application
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.seprojectsemester5.R
import com.example.seprojectsemester5.models.*
import com.example.seprojectsemester5.repositories.DataAnalystRepository
import com.example.seprojectsemester5.repositories.RemoteDataSource
import com.example.seprojectsemester5.repositories.remote.DataAnalystApi
import com.example.seprojectsemester5.repositories.remote.Resource
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.launch

class DataAnalystMainActivityViewModel(application: Application) : AndroidViewModel(application) {

    fun getDiseaseArray() : Array<String> {
        return getApplication<Application>()
            .resources
            .getStringArray(R.array.data_analyst_disease_type_array)
    }

    fun getDataAnalystDiseaseAdapter(): ArrayAdapter<String> {
        val diseasesArray = getDiseaseArray()

        return ArrayAdapter(
            getApplication(),
            R.layout.data_analyst_filters_disease_drop_down_item,
            diseasesArray
        )
    }

    // repository
    private val repository = DataAnalystRepository(
        RemoteDataSource().buildApi(DataAnalystApi::class.java))

        // get summary data
    private val _getDataSummaryResponse = MutableLiveData<Resource<DataAnalystGetDataSummary>>()
    val getDataSummaryResponse : LiveData<Resource<DataAnalystGetDataSummary>>
        get() = _getDataSummaryResponse

    fun getDataSummary(diseaseFilters: DiseaseFilters) = viewModelScope.launch {
        _getDataSummaryResponse.value = repository.getSummaryData(diseaseFilters)
    }

        // update summary data
    private val _updateSummaryDataResponse = MutableLiveData<Resource<MessageResponse>>()
    val updateSummaryDataResponse : LiveData<Resource<MessageResponse>>
        get() = _updateSummaryDataResponse

    fun updateSummaryDataResponse(authToken : String, pinCode : Int) = viewModelScope.launch {
        _updateSummaryDataResponse.value = repository.updateSummaryData(authToken, pinCode)
    }

    // pieChartData
    private val _pieChartEntries = MutableLiveData<CustomPieEntry>()
    val pieChartEntries : LiveData<CustomPieEntry>
        get() = _pieChartEntries

    fun createPieChartEntries(
        diseaseType : String,
        dataAnalystDataSummary : DataAnalystDataSummary
    ) {
        val criteria = arrayOf("None", "Mild", "Moderate", "Severe")

        val entries = listOf(
            PieEntry(dataAnalystDataSummary.safe.toFloat(), criteria[0]),
            PieEntry(dataAnalystDataSummary.mild.toFloat(), criteria[1]),
            PieEntry(dataAnalystDataSummary.moderate.toFloat(), criteria[2]),
            PieEntry(dataAnalystDataSummary.sever.toFloat(), criteria[3])
        )

        _pieChartEntries.value = CustomPieEntry(diseaseType, entries)
    }
}