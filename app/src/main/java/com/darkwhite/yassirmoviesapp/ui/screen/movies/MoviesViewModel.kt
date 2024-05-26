package com.darkwhite.yassirmoviesapp.ui.screen.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.darkwhite.yassirmoviesapp.data.model.Movie
import com.darkwhite.yassirmoviesapp.utils.Constants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(

) : ViewModel() {
  
  var uiState by mutableStateOf(MoviesUiState())
    private set
  
  init {
  
  }
  
  fun onUiEvent(event: MoviesUiEvent) {
    when (event) {
      MoviesUiEvent.OnFetchMore -> fetchMore()
      MoviesUiEvent.OnErrorMessageShown -> resetErrorMessage()
      is MoviesUiEvent.OnSearchQueryChange -> updateSearchQuery(event.newValue)
    }
  }
  
  private fun fetchMore() {
  
  }
  
  private fun updateSearchQuery(newValue: String) {
  
  }
  
  private fun resetErrorMessage() {
    uiState = uiState.copy(errorMessage = EMPTY_STRING)
  }
  
}

sealed interface MoviesUiEvent {
  data class OnSearchQueryChange(val newValue: String) : MoviesUiEvent
  data object OnFetchMore : MoviesUiEvent
  data object OnErrorMessageShown : MoviesUiEvent
}

data class MoviesUiState(
  val isInitialLoading: Boolean = true,
  val isLoading: Boolean = false,
  val pageIndex: Int = 0,
  val movies: List<Movie> = emptyList(),
  val searchQuery: String = EMPTY_STRING,
  val errorMessage: String = EMPTY_STRING,
)