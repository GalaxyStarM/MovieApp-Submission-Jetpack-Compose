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
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    private val _movie = MutableStateFlow<UiState<Movie>>(UiState.Loading)
    val movie = _movie.asStateFlow()

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovie(id)
                .catch { _movie.value = UiState.Error(errorMessage = it.message.toString()) }
                .collect{ _movie.value = UiState.Success(data = it) }
        }
    }

    fun updateFavoriteMovie(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteMovie(id, isFavorite)
        }
    }
}