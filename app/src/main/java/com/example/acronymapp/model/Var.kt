package com.example.acronymapp.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Var(
    val freq: Int = 0,
    val lf: String = "",
    val since: Int = 0
) : Parcelable