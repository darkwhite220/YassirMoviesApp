package com.darkwhite.yassirmoviesapp.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.darkwhite.yassirmoviesapp.ui.component.BottomBar
import com.darkwhite.yassirmoviesapp.ui.navigation.AppState
import com.darkwhite.yassirmoviesapp.ui.navigation.Favorite
import com.darkwhite.yassirmoviesapp.ui.navigation.Movie
import com.darkwhite.yassirmoviesapp.ui.navigation.MovieDetail
import com.darkwhite.yassirmoviesapp.ui.navigation.NavHostWithFadeThough
import com.darkwhite.yassirmoviesapp.ui.navigation.Settings
import com.darkwhite.yassirmoviesapp.ui.navigation.rememberAppState
import com.darkwhite.yassirmoviesapp.ui.screen.favorite.FavoriteScreen
import com.darkwhite.yassirmoviesapp.ui.screen.movie_detail.MovieDetailScreen
import com.darkwhite.yassirmoviesapp.ui.screen.movies.MoviesScreen
import com.darkwhite.yassirmoviesapp.ui.screen.settings.SettingsScreen
import com.darkwhite.yassirmoviesapp.utils.Constants


@Composable
fun YassirMoviesApp(
  appState: AppState = rememberAppState()
) {
  Scaffold(
    bottomBar = { BottomBar(appState = appState) }
  ) { paddingValues ->
    NavHostWithFadeThough(
      navController = appState.navController,
      startDestination = Constants.HOME_ROUTE,
      modifier = Modifier
        .padding(paddingValues)
        .consumeWindowInsets(paddingValues)
    ) {
      
      homeNestedGraph(appState = appState)
      
      composable(route = Favorite.route) {
        FavoriteScreen()
      }
      
      composable(route = Settings.route) {
        SettingsScreen()
      }
    }
  }
}

private fun NavGraphBuilder.homeNestedGraph(appState: AppState) {
  navigation(startDestination = Movie.route, route = Constants.HOME_ROUTE) {
    composable(route = Movie.route) {
      MoviesScreen(
        onMovieClick = { movieId -> appState.navigateToMovieDetail(movieId = movieId) }
      )
    }
    composable(route = MovieDetail.routeWithArg, arguments = MovieDetail.args) {
      MovieDetailScreen(
        onBackClick = { appState.popBackStack() }
      )
    }
  }
}