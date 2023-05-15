package com.andi.gmovie.di

import android.content.Context
import com.andi.gmovie.data.local.MovieRepositoryLocal
import com.andi.gmovie.data.local.room.MovieDatabase
import com.andi.gmovie.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepositoryLocal {
        val database = MovieDatabase.getInstance(context)
        val dao = database.movieDao()
        val appExecutors = AppExecutors()
        return MovieRepositoryLocal.getInstance( dao, appExecutors)
    }
}