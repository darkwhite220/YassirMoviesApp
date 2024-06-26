package com.darkwhite.yassirmoviesapp.data.model

import androidx.compose.runtime.Immutable
import com.darkwhite.yassirmoviesapp.utils.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
  val page: Int,
  val results: List<Movie>
)

@Immutable
@Serializable
data class Movie(
//  val adult: Boolean,
//  @SerialName("backdrop_path")
//  val backdropPath: String,
//  @SerialName("genre_ids")
//  val genreIds: List<Int>,
  val id: Int,
//  @SerialName("original_language")
//  val originalLanguage: String,
//  @SerialName("original_title")
//  val originalTitle: String,
//  val overview: String,
//  val popularity: Double,
  @SerialName("poster_path")
  val posterPath: String,
//  @SerialName("release_date")
//  val releaseDate: String,
  val title: String,
//  val video: Boolean,
//  @SerialName("vote_average")
//  val voteAverage: Double,
//  @SerialName("vote_count")
//  val voteCount: Int
) {
  val posterUrl: String
    get() = "${Constants.TMDB_BASE_IMAGE_URL}$posterPath"
}

