package com.example.acronymapp.model

enum class Status {
    SUCCESS,
    NO_RESULTS,
    ERROR
}

data class ApiResponse<T>(
    val value: T?,
    val status: Status,
    val errorMsg: String = ""
)
