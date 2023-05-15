package com.andi.gmovie.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andi.gmovie.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getBookmarkedMovie():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun saveMovie(movie: MovieEntity)

    @Query("DELETE FROM movie WHERE id= :movieId")
     fun deleteMovie(movieId: Int)

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id= :id)")
    fun isMovieBookmarked(id: Int): Boolean
}