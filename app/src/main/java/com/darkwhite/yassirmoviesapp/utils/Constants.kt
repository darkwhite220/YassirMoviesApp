package com.darkwhite.yassirmoviesapp.utils

object Constants {
  // Query
  const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
  const val TMDB_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
  const val TMDB_API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
  
  // Navigation routes
  const val HOME_ROUTE = "home_route"
  const val MOVIE_ROUTE = "movie_route"
  const val FAVORITE_ROUTE = "favorite_route"
  const val SETTINGS_ROUTE = "settings_route"
  const val MOVIE_DETAIL_ROUTE = "movie_detail_route"
  const val MOVIE_DETAIL_ARG = "movie_detail_id"
  const val MOVIE_DETAIL_ROUTE_WITH_ARG = "$MOVIE_DETAIL_ROUTE/{${MOVIE_DETAIL_ARG}}"
  
  // Common
  const val EMPTY_STRING = ""
  
}