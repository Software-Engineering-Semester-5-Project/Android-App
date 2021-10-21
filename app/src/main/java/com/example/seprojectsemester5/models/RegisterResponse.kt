package com.example.seprojectsemester5.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class RegisterResponse(
    val status: String,
    val User : User ?= null,
    val message : String ?= null
)