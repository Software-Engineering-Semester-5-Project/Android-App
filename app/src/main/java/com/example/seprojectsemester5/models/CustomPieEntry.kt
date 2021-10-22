package com.example.seprojectsemester5.models

import android.os.Parcelable
import com.github.mikephil.charting.data.PieEntry
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomPieEntry(
    val diseaseType : String,
    val entries: List<PieEntry>?
) : Parcelable