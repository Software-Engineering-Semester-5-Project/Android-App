package com.example.seprojectsemester5.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name : String,
    val email : String ?= null,
    val password : String ?= null,
    val verify : String,
    val verifyngo : String,
    val role : String ?= null,
) : Parcelable
