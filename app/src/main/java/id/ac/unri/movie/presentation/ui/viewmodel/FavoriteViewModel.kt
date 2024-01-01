package id.ac.unri.movie.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unri.movie.domain.model.Movie
import id.ac.unri.movie.domain.repository.MovieRepository
import id.ac.unri.movie.presentation.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _allFavoriteMovies = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val allFavoriteMovies = _allFavoriteMovies.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavoriteMovies()
                .catch {
                    _allFavoriteMovies.value = UiState.Error(errorMessage = it.message.toString())
                }
                .collect{ _allFavoriteMovies.value = UiState.Success(data = it)}
        }
    }
}