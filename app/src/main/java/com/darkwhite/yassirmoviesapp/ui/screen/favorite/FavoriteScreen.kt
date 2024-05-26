package com.darkwhite.yassirmoviesapp.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.darkwhite.yassirmoviesapp.R

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = stringResource(id = R.string.favorite),
      style = MaterialTheme.typography.headlineLarge,
    )
  }
}