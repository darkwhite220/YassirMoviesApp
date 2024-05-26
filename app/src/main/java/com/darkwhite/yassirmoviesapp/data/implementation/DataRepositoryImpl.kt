package com.darkwhite.yassirmoviesapp.data.implementation

import android.util.Log
import com.darkwhite.yassirmoviesapp.data.model.ApiResponse
import com.darkwhite.yassirmoviesapp.data.model.Movie
import com.darkwhite.yassirmoviesapp.data.model.MovieResponse
import com.darkwhite.yassirmoviesapp.data.repository.DataRepository
import com.darkwhite.yassirmoviesapp.utils.Constants.TMDB_API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
  private val httpClient: HttpClient
) : DataRepository {
  
  override suspend fun fetchMovies(pageIndex: Int): ApiResponse<List<Movie>> {
    return httpCallWrapper {
      val response = httpClient.get {
        url {
          path("discover/movie")
          if (pageIndex > 0) {
            encodedParameters.append("page", "$pageIndex")
          }
          encodedParameters.append("api_key", TMDB_API_KEY)
        }
      }
      Log.d("TAG", "fetchMovies: $response")
      response.body<MovieResponse>()
        .results
    }
  }
  
  private inline fun <T> httpCallWrapper(apiCall: () -> T): ApiResponse<T> {
    return try {
      ApiResponse.Success(data = apiCall())
    } catch (e: Exception) {
      Log.e("TAG", "httpCallWrapper: ${e.message}", e)
      ApiResponse.Failure(exception = e)
    }
  }
}