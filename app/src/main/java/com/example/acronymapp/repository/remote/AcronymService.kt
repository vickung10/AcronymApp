package com.example.acronymapp.repository.remote

import com.example.acronymapp.model.AcronymResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("/software/acromine/dictionary.py")
    suspend fun getAcronym(@Query("sf") acronym: String): Response<List<AcronymResponse>>
}