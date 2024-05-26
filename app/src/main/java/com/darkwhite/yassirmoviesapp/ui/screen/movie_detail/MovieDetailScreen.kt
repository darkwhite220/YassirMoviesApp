package com.darkwhite.yassirmoviesapp.ui.screen.movie_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MovieDetailScreen(
  modifier: Modifier = Modifier,
  onBackClick: () -> Unit = {},
) {
  Text(
    modifier = modifier.fillMaxSize(),
    text = "movie detail",
    style = MaterialTheme.typography.headlineLarge,
  )
}