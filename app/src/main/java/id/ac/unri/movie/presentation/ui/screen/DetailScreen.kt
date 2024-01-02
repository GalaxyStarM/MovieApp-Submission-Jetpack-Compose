package id.ac.unri.movie.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ac.unri.movie.R
import id.ac.unri.movie.domain.model.Movie
import id.ac.unri.movie.presentation.ui.component.TextDetail
import id.ac.unri.movie.presentation.ui.theme.MovieTheme
import id.ac.unri.movie.presentation.ui.viewmodel.DetailViewModel
import id.ac.unri.movie.presentation.util.UiState

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    viewModel.movie.collectAsState(initial = UiState.Loading).value.let { uiState  ->
        when(uiState) {
            is UiState.Loading -> viewModel.getMovie(movieId)
            is UiState.Success -> MovieDetailContent(
                movie = uiState.data,
                onAddToFavoriteClicked = viewModel::updateFavoriteMovie,
                onBackClicked = navigateBack
            )
            is UiState.Error -> {}

        }
    }
}


@Composable
fun MovieDetailContent(
    movie: Movie,
    onAddToFavoriteClicked: (id: Int, isFavorite: Boolean) -> Unit,
    onBackClicked: () -> Unit
) {
    val (id, title, genre, rating, lyrical, director, origin,
        language, duration, moviePicture, description, isFavorite
    ) = movie

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = moviePicture),
                contentDescription = title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier.wrapContentSize(),
                            color = Color(0xFFFCC71F)
                        ) {
                            Text(
                                text = "IMDB 7.0",
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = "favorite",
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                        )

                        Text(
                            text = rating,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Text(
                            text = "$duration min",
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                        )
                    }

                    Icon(
                        painter = if (isFavorite) painterResource(id = R.drawable.ic_favorite)
                            else painterResource(id = R.drawable.ic_favorite_border),
                        contentDescription = null,
                        tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onAddToFavoriteClicked(id ?: 0, !isFavorite)
                            }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Image(
                        painter = painterResource(id = origin),
                        contentDescription = null,
                        alignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.alpha(0.6f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextDetail(category = "Director", detail = director)
                Spacer(modifier = Modifier.height(4.dp))
                TextDetail(category = "Genre", detail = genre)
                Spacer(modifier = Modifier.height(4.dp))
                TextDetail(category = "Lyrical", detail = lyrical)
                Spacer(modifier = Modifier.height(4.dp))
                TextDetail(category = "Languange", detail = language)
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alpha(0.8f)
                )
            }
        }

        IconButton(
            onClick = onBackClicked,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .size(40.dp)
                .testTag("back_button")
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailPreview() {
    val sampleMovie = Movie(
        id = 1,
        title = "Inception",
        genre = "Science Fiction",
        rating = "8.8",
        lyrical = "July 16, 2010",
        director = "Christopher Nolan",
        origin = R.drawable.flag_us,
        language = "English",
        duration = 148,
        moviePicture = R.drawable.mv_interstellar,
        description = "Inception is a science fiction action film...",
        isFavorite = false
    )

    MovieTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieDetailContent(
                movie = sampleMovie,
                onAddToFavoriteClicked = { _, _ ->  },
                onBackClicked = {  }
            )
        }
    }
}
