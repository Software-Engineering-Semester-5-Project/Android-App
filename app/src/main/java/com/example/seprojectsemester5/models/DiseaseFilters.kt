package com.example.seprojectsemester5.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiseaseFilters (
    var startDate : Int = 0,
    var endDate : Int = 100,
    val pin : Int, // pin is immutable
    var sex : String = "all",
    var diseaseType : String = "fever",
    var restDisease : String = "",
) : Parcelable