package com.example.seprojectsemester5.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataAnalystDataSummary(
    val end: String,
    val gender: String,
    val mild: Int,
    val moderate: Int,
    val pin: String,
    val safe: Int,
    val sever: Int,
    val start: String
) : Parcelable