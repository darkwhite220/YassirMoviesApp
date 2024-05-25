package com.darkwhite.yassirmoviesapp.data.model

sealed interface ApiResponse<T> {
  data class Success<T>(val data: T) : ApiResponse<T>
  data class Failure<T>(val exception: Exception) : ApiResponse<T>
}