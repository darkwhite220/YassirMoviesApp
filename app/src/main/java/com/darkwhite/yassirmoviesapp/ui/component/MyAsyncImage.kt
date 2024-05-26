package com.darkwhite.yassirmoviesapp.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.darkwhite.yassirmoviesapp.R


@Preview
@Composable
fun MyAsyncImage(modifier: Modifier = Modifier, imageUrl: String = "") {
  AsyncImage(
    modifier = modifier,
    model = imageUrl,
    placeholder = BrushPainter(
      Brush.linearGradient(
        listOf(
          Color(color = 0xFFFFFFFF),
          Color(color = 0xFFDDDDDD),
        )
      )
    ),
    contentDescription = stringResource(R.string.movie_poster),
    contentScale = ContentScale.FillBounds
  )
}