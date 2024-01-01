package id.ac.unri.movie.presentation.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unri.movie.data.data_source.MovieDataSource
import id.ac.unri.movie.domain.model.Movie
import id.ac.unri.movie.domain.repository.MovieRepository
import id.ac.unri.movie.presentation.util.MovieState
import id.ac.unri.movie.presentation.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){

    private val _allMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val allMovies = _allMovies.asStateFlow()

    private val _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovies().collect { movie ->
                when(movie.isEmpty()) {
                    true -> repository.insertAllMovie(MovieDataSource.dummyMovie)
                    else -> _allMovies.value = UiState.Success(movie)
                }
            }
        }
    }

    private fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchMovie(query)
                .catch { _allMovies.value = UiState.Error(errorMessage = it.message.toString()) }
                .collect { _allMovies.value = UiState.Success(data = it)}
        }
    }

    fun onQueryChange(query: String) {
        _movieState.value = _movieState.value.copy(query = query)
        searchMovie(query)
    }
}