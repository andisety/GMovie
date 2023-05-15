package com.andi.gmovie.data.local

import com.andi.gmovie.data.local.entity.MovieEntity
import com.andi.gmovie.data.local.room.MovieDao
import com.andi.gmovie.model.response.ResponseDetail
import com.andi.gmovie.utils.AppExecutors

class MovieRepositoryLocal private constructor(private val movieDao: MovieDao,private val appExecutors: AppExecutors)  {



    //get list favorite
    var movie = emptyList<MovieEntity>()
    fun getFavorite():List<MovieEntity>{
        appExecutors.diskIO.execute {
            movie = movieDao.getBookmarkedMovie()
        }
        return movie
    }

    //save ke database
    fun addFavorite(movie: ResponseDetail, favoriteState: Boolean) {
        appExecutors.diskIO.execute {
            val movieFavorite = MovieEntity(
                movie.id,
                movie.title,
                movie.posterPath,
                movie.overview,
                favoriteState
            )
            movieDao.saveMovie(movieFavorite)
        }
    }


    //cek ada di database
       fun getMovie(id:Int): Boolean {
        var fav= false
        movie.forEach {
           if ( it.id==id){
                fav=true
           }
        }
        return fav
    }

    //delete
   fun deleteMovie(id:Int){
        appExecutors.diskIO.execute {
            movieDao.deleteMovie(id)
        }
    }


    companion object {
        @Volatile
        private var instance: MovieRepositoryLocal? = null
        fun getInstance(
            movieDao: MovieDao,
            appExecutors: AppExecutors
        ): MovieRepositoryLocal =
            instance ?: synchronized(this) {
                instance ?: MovieRepositoryLocal(movieDao, appExecutors)
            }.also { instance = it }
    }


}