package id.ac.unri.movie.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val genre: String,
    val rating: String,
    val lyrical: String,
    val director: String,
    val origin: Int,
    val language: String,
    val duration: Int,
    val moviePicture: Int,
    val description: String,
    val isFavorite: Boolean = false
)
