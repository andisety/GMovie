package com.andi.gmovie.di

import android.content.Context
import com.andi.gmovie.data.MovieRepository
import com.andi.gmovie.data.local.MovieRepositoryLocal
import com.andi.gmovie.data.local.room.MovieDatabase
import com.andi.gmovie.network.ApiService
import com.andi.gmovie.network.RetrofitClient
import com.andi.gmovie.utils.AppExecutors

object InjectionOnline {
    fun provideRepository(): MovieRepository {
        val apiservice = RetrofitClient.getApiService()
        return MovieRepository.getInstance(apiservice )
    }
}