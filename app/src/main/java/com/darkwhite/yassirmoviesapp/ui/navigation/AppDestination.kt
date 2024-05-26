package com.darkwhite.yassirmoviesapp.ui.navigation

import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.navArgument
import com.darkwhite.yassirmoviesapp.utils.Constants.FAVORITE_ROUTE
import com.darkwhite.yassirmoviesapp.utils.Constants.MOVIE_DETAIL_ARG
import com.darkwhite.yassirmoviesapp.utils.Constants.MOVIE_DETAIL_ROUTE
import com.darkwhite.yassirmoviesapp.utils.Constants.MOVIE_DETAIL_ROUTE_WITH_ARG
import com.darkwhite.yassirmoviesapp.utils.Constants.MOVIE_ROUTE
import com.darkwhite.yassirmoviesapp.utils.Constants.SETTINGS_ROUTE

sealed interface AppDestination {
  val route: String
}

data object Movie : AppDestination {
  override val route: String
    get() = MOVIE_ROUTE
}

data object MovieDetail : AppDestination {
  override val route: String
    get() = MOVIE_DETAIL_ROUTE
  
  const val routeWithArg = MOVIE_DETAIL_ROUTE_WITH_ARG
  val args = listOf(
    navArgument(name = MOVIE_DETAIL_ARG) { type = IntType }
  )
}

data object Favorite : AppDestination {
  override val route: String
    get() = FAVORITE_ROUTE
}

data object Settings : AppDestination {
  override val route: String
    get() = SETTINGS_ROUTE
}
