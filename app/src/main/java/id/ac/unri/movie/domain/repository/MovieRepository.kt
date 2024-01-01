package id.ac.unri.movie.domain.repository

import id.ac.unri.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getAllMovies(): Flow<List<Movie>>

    fun getAllFavoriteMovies(): Flow<List<Movie>>

    fun getMovie(id: Int): Flow<Movie>

    fun searchMovie(query: String): Flow<List<Movie>>

    suspend fun insertAllMovie(driverList: List<Movie>)

    suspend fun updateFavoriteMovie(id: Int, isFavorite: Boolean)
}