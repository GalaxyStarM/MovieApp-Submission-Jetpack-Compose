package id.ac.unri.movie.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import id.ac.unri.movie.presentation.ui.theme.MovieTheme

@Composable
fun TextDetail(
    category: String,
    detail: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = detail,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .weight(1f)
                .alpha(0.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextDetailPreview() {
    MovieTheme {
        TextDetail(category = "Genre", detail = "Adventure · Comedy · Family · Fantasy · Musical · Romance")
    }
}