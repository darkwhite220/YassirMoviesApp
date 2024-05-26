package com.darkwhite.yassirmoviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.darkwhite.yassirmoviesapp.ui.YassirMoviesApp
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
import com.darkwhite.yassirmoviesapp.ui.theme.YassirMoviesAppTheme
import com.darkwhite.yassirmoviesapp.utils.Constants.HOME_ROUTE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      YassirMoviesAppTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          YassirMoviesApp()
        }
      }
    }
  }
}
