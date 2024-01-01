package id.ac.unri.movie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unri.movie.domain.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDao
}