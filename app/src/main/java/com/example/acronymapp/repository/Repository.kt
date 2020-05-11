package com.example.acronymapp.repository

import com.example.acronymapp.model.AcronymResponse
import com.example.acronymapp.model.ApiResponse
import com.example.acronymapp.model.Status
import com.example.acronymapp.repository.remote.RetrofitInstance

class Repository {
    private val api by lazy { RetrofitInstance.acronymService }
    private val acronymRunner = ControlledRunner<ApiResponse<AcronymResponse>>()

    suspend fun fetchAcronym(acronym: String): ApiResponse<AcronymResponse> =
        try {
            acronymRunner.cancelPreviousThenRun {
                val response = api.getAcronym(acronym)

                if (response.body().isNullOrEmpty()) {
                    if (response.isSuccessful) {
                        return@cancelPreviousThenRun ApiResponse(
                            value = null, status = Status.NO_RESULTS, errorMsg = "No results found."
                        )
                    } else {
                        return@cancelPreviousThenRun ApiResponse(
                            value = null,
                            status = Status.ERROR,
                            errorMsg = response.errorBody().toString()
                        )
                    }
                } else {
                    return@cancelPreviousThenRun ApiResponse(
                        value = response.body()!![0],
                        status = Status.SUCCESS
                    )
                }

            }
        } catch (ex: Exception) {
            ApiResponse(
                value = null,
                status = Status.ERROR,
                errorMsg = ex.message ?: "Oops, something went wrong."
            )
        }
}