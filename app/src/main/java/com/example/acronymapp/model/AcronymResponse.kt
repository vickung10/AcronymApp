package com.example.acronymapp.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class AcronymResponse(
    val lfs: List<Lfs> = listOf(),
    val sf: String = ""
) : Parcelable