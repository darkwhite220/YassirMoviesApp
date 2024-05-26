package com.darkwhite.yassirmoviesapp.data.repository

import com.darkwhite.yassirmoviesapp.data.model.ApiResponse
import com.darkwhite.yassirmoviesapp.data.model.Movie
import com.darkwhite.yassirmoviesapp.data.model.MovieDetail

interface DataRepository {
  
  suspend fun fetchMovies(pageIndex: Int): ApiResponse<List<Movie>>
  
  suspend fun fetchMovieDetail(movieId: Int): ApiResponse<MovieDetail>
  
}