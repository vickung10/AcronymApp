package com.example.acronymapp.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Lfs(
    val freq: Int = 0,
    val lf: String = "",
    val since: Int = 0,
    val vars: List<Var> = listOf()
) : Parcelable