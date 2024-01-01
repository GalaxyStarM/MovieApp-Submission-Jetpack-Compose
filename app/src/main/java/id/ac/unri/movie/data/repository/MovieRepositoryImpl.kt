package id.ac.unri.movie.data.repository

import id.ac.unri.movie.data.local.MovieDao
import id.ac.unri.movie.domain.model.Movie
import id.ac.unri.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieRepository{

    override fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()

    override fun getAllFavoriteMovies(): Flow<List<Movie>> = movieDao.getAllFavoriteMovies()

    override fun getMovie(id: Int): Flow<Movie> = movieDao.getMovie(id)

    override fun searchMovie(query: String): Flow<List<Movie>> = movieDao.searchMovie(query)

    override suspend fun insertAllMovie(movieList: List<Movie>) = movieDao.insertAllMovie(movieList)

    override suspend fun updateFavoriteMovie(id: Int, isFavorite: Boolean) = movieDao.updateFavoriteMovie(id, isFavorite)
}