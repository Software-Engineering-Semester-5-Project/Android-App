package com.example.seprojectsemester5.models

data class DataAnalystGetDataSummary(
    val `data`: DataAnalystDataSummary ?= null,
    val status: String,
    val message : String ?= null,
)