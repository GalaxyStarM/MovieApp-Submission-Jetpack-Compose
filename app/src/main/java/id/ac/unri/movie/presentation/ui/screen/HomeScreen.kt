package id.ac.unri.movie.presentation.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ac.unri.movie.data.data_source.MovieDataSource
import id.ac.unri.movie.domain.model.Movie
import id.ac.unri.movie.presentation.ui.component.EmptyContent
import id.ac.unri.movie.presentation.ui.component.LoadingIndicator
import id.ac.unri.movie.presentation.ui.component.MovieItem
import id.ac.unri.movie.presentation.ui.component.SearchBar
import id.ac.unri.movie.presentation.ui.theme.MovieTheme
import id.ac.unri.movie.presentation.ui.viewmodel.HomeViewModel
import id.ac.unri.movie.presentation.util.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int?) -> Unit
){
    val movieState by viewModel.movieState
    viewModel.allMovies.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> HomeContent(
                movies = uiState.data,
                query = movieState.query,
                onQueryChange = viewModel::onQueryChange,
                navigateToDetail = navigateToDetail,
                modifier = modifier
            )
            is UiState.Error -> EmptyContent()
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    query: String,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (Int?) -> Unit
){
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
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
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeContentLightPreview() {
    MovieTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeContent(
                movies = MovieDataSource.dummyMovie,
                query = "",
                onQueryChange = {},
                navigateToDetail = {}
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeContentNightPreview() {
    MovieTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeContent(
                movies = MovieDataSource.dummyMovie,
                query = "",
                onQueryChange = {},
                navigateToDetail = {}
            )
        }
    }
}