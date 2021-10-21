package com.example.seprojectsemester5.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id : String ?= null,
    val name : String ?= null, // can be null
    val email : String,
    val password : String,
    val verify : String ?= null,
    val verifyngo : String ?= null,
    val role : String,
    val __v : Int ?= null,
    val jwt : String ?= null
) : Parcelable
