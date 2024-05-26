package com.darkwhite.yassirmoviesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.darkwhite.yassirmoviesapp.ui.component.TopLevelDestination
import com.darkwhite.yassirmoviesapp.utils.Constants.FAVORITE_ROUTE
import com.darkwhite.yassirmoviesapp.utils.Constants.HOME_ROUTE
import com.darkwhite.yassirmoviesapp.utils.Constants.SETTINGS_ROUTE


@Composable
fun rememberAppState(
  navController: NavHostController = rememberNavController()
): AppState =
  remember(navController) {
    AppState(navController = navController)
  }

@Stable
class AppState(
  val navController: NavHostController
) {
  
  private val currentDestination: NavDestination?
    @Composable get() = navController
      .currentBackStackEntryAsState().value?.destination
  
  val currentTopLevelDestination: TopLevelDestination?
    @Composable get() = when (currentDestination?.route) {
      TopLevelDestination.HOME.route -> TopLevelDestination.HOME
      TopLevelDestination.FAVORITE.route -> TopLevelDestination.FAVORITE
      TopLevelDestination.SETTINGS.route -> TopLevelDestination.SETTINGS
      else -> null
    }
  
  val topLevelDestination: List<TopLevelDestination> = TopLevelDestination.entries.toList()
  
  fun navigate(destination: TopLevelDestination) {
    val navOptions = navOptions {
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
      }
      launchSingleTop = true
      restoreState = true
    }
    
    when (destination) {
      TopLevelDestination.HOME -> navigateToHome(navOptions = navOptions)
      TopLevelDestination.FAVORITE -> navigateToEstimate(navOptions = navOptions)
      TopLevelDestination.SETTINGS -> navigateToSettings(navOptions = navOptions)
    }
  }
  
  private fun navigateToHome(navOptions: NavOptions) {
    navigationWrapper {
      navController.navigate(route = HOME_ROUTE, navOptions = navOptions)
    }
  }
  
  private fun navigateToEstimate(navOptions: NavOptions) {
    navigationWrapper {
      navController.navigate(route = FAVORITE_ROUTE, navOptions = navOptions)
    }
  }
  
  private fun navigateToSettings(navOptions: NavOptions) {
    navigationWrapper {
      navController.navigate(route = SETTINGS_ROUTE, navOptions = navOptions)
    }
  }
  
  fun navigateToMovieDetail(movieId: Int) {
    navigationWrapper {
      navController.navigate(route = "${MovieDetail.route}/$movieId")
    }
  }
  
  fun popBackStack() {
    navigationWrapper {
      navController.popBackStack()
    }
  }
  
  // Make sure current screen is on resume state before registering the navigation
  private inline fun navigationWrapper(navigation: () -> Unit) {
    if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
      navigation()
    }
  }
}