package com.example.seprojectsemester5.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val status: String,
    val token: String ?= null,
    val message : String ?= null,
) : Parcelable