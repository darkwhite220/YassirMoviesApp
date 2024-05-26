package com.darkwhite.yassirmoviesapp.data.repository

import com.darkwhite.yassirmoviesapp.data.model.ApiResponse
import com.darkwhite.yassirmoviesapp.data.model.Movie

interface DataRepository {
  
  suspend fun fetchMovies(pageIndex: Int): ApiResponse<List<Movie>>
  
}