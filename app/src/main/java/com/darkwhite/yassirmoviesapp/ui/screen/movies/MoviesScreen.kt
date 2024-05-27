package com.darkwhite.yassirmoviesapp.ui.screen.movies

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.darkwhite.yassirmoviesapp.data.model.Movie
import com.darkwhite.yassirmoviesapp.ui.component.BodySmallText
import com.darkwhite.yassirmoviesapp.ui.component.LoadingIndicator
import com.darkwhite.yassirmoviesapp.ui.component.MyAsyncImage
import com.darkwhite.yassirmoviesapp.ui.component.largeDp
import com.darkwhite.yassirmoviesapp.ui.component.mediumDp
import com.darkwhite.yassirmoviesapp.ui.theme.YassirMoviesAppTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MoviesScreen(
  onMovieClick: (movieId: Int) -> Unit = {},
  viewModel: MoviesViewModel = hiltViewModel()
) {
  MoviesContent(
    uiState = viewModel.uiState,
    onUiEvent = viewModel::onUiEvent,
    onMovieClick = onMovieClick,
  )
}

@Composable
fun MoviesContent(
  modifier: Modifier = Modifier,
  uiState: MoviesUiState = MoviesUiState(),
  onUiEvent: (MoviesUiEvent) -> Unit = {},
  onMovieClick: (movieId: Int) -> Unit = {},
) {
  val snackbarHostState = remember { SnackbarHostState() }
  val lazyState = rememberLazyGridState()
  var currentIndex by remember { mutableIntStateOf(0) }
  val loadMore by remember(currentIndex, uiState.movies) {
    val threshold = 4
    derivedStateOf {
      uiState.movies.isNotEmpty() && !uiState.isLoading && !uiState.isInitialLoading
          && currentIndex + threshold >= uiState.movies.size
    }
  }
  
  // Initial loading indicator
  if (uiState.isInitialLoading) {
    LoadingIndicator(modifier = Modifier.fillMaxHeight())
  }
  
  Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
    LazyVerticalGrid(
      modifier = modifier.padding(paddingValues),
      state = lazyState,
      columns = GridCells.Fixed(2),
      contentPadding = PaddingValues(largeDp),
      horizontalArrangement = Arrangement.spacedBy(largeDp),
      verticalArrangement = Arrangement.spacedBy(largeDp),
    ) {
      itemsIndexed(
        items = uiState.movies,
        key = { index, _ -> index }
      ) { index: Int, movie: Movie ->
        currentIndex = index
        MovieUi(
          movie = movie,
          onClick = onMovieClick
        )
      }
      
      item(span = { GridItemSpan(2) }) {
        // Loading more movies indicator
        if (uiState.isLoading) {
          LoadingIndicator()
        }
      }
    }
  }
  
  LaunchedEffect(uiState.errorMessage) {
    if (uiState.errorMessage.isNotEmpty()) {
      snackbarHostState.showSnackbar(uiState.errorMessage)
      onUiEvent(MoviesUiEvent.OnErrorMessageShown)
    }
  }
  
  LaunchedEffect(key1 = loadMore) {
    if (loadMore) {
      onUiEvent(MoviesUiEvent.OnFetchMore)
    }
  }
}

@Composable
private fun MovieUi(
  movie: Movie,
  onClick: (movieId: Int) -> Unit
) {
  Column(
    modifier = Modifier
      .clip(RoundedCornerShape(largeDp))
      .clickable { onClick(movie.id) },
    verticalArrangement = Arrangement.spacedBy(mediumDp)
  ) {
    MyAsyncImage(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(largeDp)),
      imageUrl = movie.posterUrl,
    )
    // Title
    BodySmallText(
      modifier = Modifier.padding(horizontal = mediumDp),
      text = movie.title,
      maxLines = 1,
    )
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MoviesContentPreview(modifier: Modifier = Modifier) {
  val uiState = MoviesUiState(
    movies = List(10) {
      Movie(it, "", "Movie $it")
    }.toImmutableList()
  )
  YassirMoviesAppTheme {
    Surface {
      MoviesContent(
        uiState = uiState,
        onUiEvent = {},
        onMovieClick = {},
      )
    }
  }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun LoadingIndicatorPreview(modifier: Modifier = Modifier) {
  YassirMoviesAppTheme {
    Surface {
      LoadingIndicator()
    }
  }
}