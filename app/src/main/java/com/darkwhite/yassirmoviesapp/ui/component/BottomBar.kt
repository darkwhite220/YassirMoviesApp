package com.darkwhite.yassirmoviesapp.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.darkwhite.yassirmoviesapp.R
import com.darkwhite.yassirmoviesapp.ui.navigation.AppState
import com.darkwhite.yassirmoviesapp.ui.navigation.Favorite
import com.darkwhite.yassirmoviesapp.ui.navigation.Movie
import com.darkwhite.yassirmoviesapp.ui.navigation.Settings
import com.darkwhite.yassirmoviesapp.ui.theme.AppIcon

@Composable
fun BottomBar(
  appState: AppState
) {
  if (appState.currentTopLevelDestination != null) {
    NavigationBar(
      modifier = Modifier.fillMaxWidth(),
      containerColor = Color.Transparent,
    ) {
      appState.topLevelDestination.forEach { item ->
        val isSelected = appState.currentTopLevelDestination == item
        val targetIcon = if (isSelected) item.selectedIcon else item.unselectedIcon
        NavigationBarItem(
          selected = isSelected,
          onClick = { appState.navigate(item) },
          icon = {
            Icon(
              painter = painterResource(targetIcon),
              contentDescription = null
            )
          },
          label = { Text(text = stringResource(item.titleTextId)) },
        )
      }
    }
  }
}

enum class TopLevelDestination(
  @DrawableRes val selectedIcon: Int,
  @DrawableRes val unselectedIcon: Int,
  @StringRes val titleTextId: Int,
  val route: String,
) {
  HOME(
    selectedIcon = AppIcon.HomeFilledIcon,
    unselectedIcon = AppIcon.HomeOutlinedIcon,
    titleTextId = R.string.home,
    route = Movie.route,
  ),
  FAVORITE(
    selectedIcon = AppIcon.FavoriteFilledIcon,
    unselectedIcon = AppIcon.FavoriteOutlinedIcon,
    titleTextId = R.string.favorite,
    route = Favorite.route,
  ),
  SETTINGS(
    selectedIcon = AppIcon.SettingsFilledIcon,
    unselectedIcon = AppIcon.SettingsOutlinedIcon,
    titleTextId = R.string.settings,
    route = Settings.route,
  ),
}
