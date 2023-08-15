package com.example.skyfysample.data.repository

sealed class DataResult<out T> {
    data class Success<T>(val body: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()
    object Loading : DataResult<Nothing>()
}