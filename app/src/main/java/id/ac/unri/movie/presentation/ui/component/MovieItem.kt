package id.ac.unri.movie.presentation.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.ac.unri.movie.R
import id.ac.unri.movie.presentation.ui.theme.MovieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    title : String,
    genre : String,
    rating: String,
    moviePicture: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.Transparent,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(10.dp),
        shadowElevation = 10.dp
    ){
        Card(
            modifier = modifier
                .size(width = 160.dp, height = 310.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = moviePicture),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ){
                Column (
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(start = 8.dp)

                    )
                    Text(
                        text = genre,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(start = 8.dp)

                    )

                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = "rating",
                            modifier = Modifier
                                .padding(start = 8.dp, bottom = 8.dp)
                        )
                        Text(
                            text = rating,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(start = 2.dp, bottom = 8.dp, top = 1.dp)
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MovieItemLightPreview() {
    MovieTheme {
        MovieItem(
            title = "Parasite",
            genre = "Comedy · Thriller · Drama",
            rating = "7.4",
            moviePicture = R.drawable.mv_parasite
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MovieItemDarkPreview() {
    MovieTheme {
        MovieItem(
            title = "Parasite",
            genre = "Comedy · Thriller · Drama",
            rating = "7.4",
            moviePicture = R.drawable.mv_parasite
        )
    }
}