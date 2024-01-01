package id.ac.unri.movie.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.unri.movie.data.local.MovieDao
import id.ac.unri.movie.data.local.MovieDatabase
import id.ac.unri.movie.data.repository.MovieRepositoryImpl
import id.ac.unri.movie.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room
        .databaseBuilder(app, MovieDatabase::class.java, "movie.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideMovieDao(movieDb: MovieDatabase) = movieDb.movieDao()

    @Provides
    @Singleton
    fun provideMovieRepository(movieDao: MovieDao): MovieRepository {
        return MovieRepositoryImpl(movieDao)
    }
}