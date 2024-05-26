package com.darkwhite.yassirmoviesapp.ui.screen.movie_detail

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkwhite.yassirmoviesapp.data.model.MovieDetail
import com.darkwhite.yassirmoviesapp.data.repository.DataRepository
import com.darkwhite.yassirmoviesapp.utils.Constants
import com.darkwhite.yassirmoviesapp.utils.Constants.EMPTY_STRING
import com.darkwhite.yassirmoviesapp.utils.Constants.MOVIE_DETAIL_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
  private val dataRepository: DataRepository,
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  
  private val movieId: Int = savedStateHandle.get<Int>(MOVIE_DETAIL_ARG) ?: 0
  
  var uiState by mutableStateOf(MovieDetailUiState())
    private set
  
  init {
    viewModelScope.launch {
      var data: MovieDetail? = null
      var errorMessage: String = EMPTY_STRING
      dataRepository.fetchMovieDetail(movieId)
        .onSuccess { movieDetail ->
          data = movieDetail
        }
        .onFailure { exception ->
          errorMessage = exception.message ?: "Fetch movie detail request failed"
        }
      uiState = uiState.copy(
        isLoading = false,
        movieDetail = data,
        errorMessage = errorMessage
      )
    }
  }
  
}

@Immutable
data class MovieDetailUiState(
  val isLoading: Boolean = true,
  val movieDetail: MovieDetail? = null,
  val errorMessage: String = Constants.EMPTY_STRING,
)