package id.ac.unri.movie.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ac.unri.movie.domain.model.Movie
import id.ac.unri.movie.presentation.ui.component.EmptyContent
import id.ac.unri.movie.presentation.ui.component.LoadingIndicator
import id.ac.unri.movie.presentation.ui.component.MovieItem
import id.ac.unri.movie.presentation.ui.viewmodel.FavoriteViewModel
import id.ac.unri.movie.presentation.util.UiState

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int?) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
){
    viewModel.allFavoriteMovies.collectAsState(initial =  UiState.Loading).value.let { uiState  ->
        when(uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> FavoriteContent(
                favoriteMovies = uiState.data,
                navigateToDetail = navigateToDetail,
                modifier = modifier
            )
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    favoriteMovies: List<Movie>,
    navigateToDetail: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    when(favoriteMovies.isEmpty()) {
        true -> EmptyContent()
        false -> FavoriteList(
            movies = favoriteMovies,
            navigateToDetail = navigateToDetail,
            modifier = modifier
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteList(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    navigateToDetail: (Int?) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(movies) { movie ->
            MovieItem(
                title = movie.title,
                genre = movie.genre,
                rating = movie.rating,
                moviePicture = movie.moviePicture,
                modifier = Modifier.clickable {
                    navigateToDetail(movie.id)
                }
            )
        }
    }
}