package com.darkwhite.yassirmoviesapp.ui.screen.movies

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkwhite.yassirmoviesapp.data.model.Movie
import com.darkwhite.yassirmoviesapp.data.repository.DataRepository
import com.darkwhite.yassirmoviesapp.utils.Constants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(
  private val dataRepository: DataRepository,
) : ViewModel() {
  
  var uiState by mutableStateOf(MoviesUiState())
    private set
  
  init {
    fetchMovies()
  }
  
  fun onUiEvent(event: MoviesUiEvent) {
    when (event) {
      MoviesUiEvent.OnFetchMore -> fetchMovies()
      MoviesUiEvent.OnErrorMessageShown -> resetErrorMessage()
    }
  }
  
  private fun fetchMovies() = viewModelScope.launch {
    if (uiState.pageIndex > 1) {
      uiState = uiState.copy(isLoading = true)
    }
    
    var errorMessage = EMPTY_STRING
    val tempList = mutableSetOf<Movie>().apply { addAll(uiState.movies) }
    
    dataRepository.fetchMovies(uiState.pageIndex)
      .onSuccess { moviesList ->
        tempList.addAll(moviesList)
      }.onFailure { exception ->
        errorMessage = exception.message ?: "Fetch movies request failed"
      }
    
    // Update
    uiState = uiState.copy(
      isInitialLoading = false,
      isLoading = false,
      pageIndex = uiState.pageIndex + 1,
      movies = tempList.toList().toImmutableList(),
      errorMessage = errorMessage,
    )
  }
  
  private fun resetErrorMessage() {
    uiState = uiState.copy(errorMessage = EMPTY_STRING)
  }
  
}

sealed interface MoviesUiEvent {
  data object OnFetchMore : MoviesUiEvent
  data object OnErrorMessageShown : MoviesUiEvent
}

@Immutable
data class MoviesUiState(
  val isInitialLoading: Boolean = true,
  val isLoading: Boolean = false,
  val pageIndex: Int = 1,
  val movies: ImmutableList<Movie> = persistentListOf(),
  val errorMessage: String = EMPTY_STRING,
)