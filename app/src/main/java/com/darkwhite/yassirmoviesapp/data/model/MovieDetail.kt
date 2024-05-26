package com.darkwhite.yassirmoviesapp.data.model

import androidx.compose.runtime.Immutable
import com.darkwhite.yassirmoviesapp.utils.Constants.TMDB_BASE_IMAGE_BACKDROP_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MovieDetail(
  val adult: Boolean,
  @SerialName("backdrop_path")
  val backdropPath: String?,
  @SerialName("belongs_to_collection")
  val belongsToCollection: BelongsToCollection?,
  val budget: Int,
  val genres: List<Genre>,
  val homepage: String,
  val id: Int,
  @SerialName("imdb_id")
  val imdbId: String?,
  @SerialName("origin_country")
  val originCountry: List<String>,
  @SerialName("original_language")
  val originalLanguage: String,
  @SerialName("original_title")
  val originalTitle: String,
  val overview: String,
  val popularity: Double,
  @SerialName("poster_path")
  val posterPath: String?,
  @SerialName("production_companies")
  val productionCompanies: List<ProductionCompany>,
  @SerialName("production_countries")
  val productionCountries: List<ProductionCountry>,
  @SerialName("release_date")
  val releaseDate: String,
  val revenue: Int,
  val runtime: Int,
  @SerialName("spoken_languages")
  val spokenLanguages: List<SpokenLanguage>,
  val status: String,
  val tagline: String?,
  val title: String,
  val video: Boolean,
  @SerialName("vote_average")
  val voteAverage: Double,
  @SerialName("vote_count")
  val voteCount: Int
) {
  val backdropPathUrl: String
    get() = "$TMDB_BASE_IMAGE_BACKDROP_URL$backdropPath"
  
  val genresString: String
    get() = genres.joinToString(" • ") { it.name }
  
  @Serializable
  data class BelongsToCollection(
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?
  )
  
  @Serializable
  data class Genre(
    val id: Int,
    val name: String
  )
  
  @Serializable
  data class ProductionCompany(
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String?,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
  )
  
  @Serializable
  data class ProductionCountry(
    @SerialName("iso_3166_1")
    val iso3166One: String,
    val name: String
  )
  
  @Serializable
  data class SpokenLanguage(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso3166One: String,
    val name: String
  )
}
