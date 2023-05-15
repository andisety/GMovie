package com.andi.gmovie.data

import android.util.Log
import com.andi.gmovie.model.Response
import com.andi.gmovie.model.response.ResponseDetail
import com.andi.gmovie.model.response.Result
import com.andi.gmovie.network.ApiService

class MovieRepository private constructor(private val apiService:ApiService) {

    suspend fun getMovieListUpcoming(): Response<List<Result>> {
        return try {
            val client = apiService.getMovieListUpcoming()
            Response.Success(client.results)
        } catch (e: Exception) {
            Log.e("ERRORR", e.message.toString())
            Response.Error(e.message.toString())
        }
    }
    suspend fun getMovieListPopular(): Response<List<Result>> {
        return try {
            val client = apiService.getMovieListPopular()
            Response.Success(client.results)
        } catch (e: Exception) {
            Log.e("ERRORR", e.message.toString())
            Response.Error(e.message.toString())
        }
    }
    suspend fun getMovieListNowPlaying(): Response<List<Result>> {
        return try {
            val client = apiService.getMovieListNowPlaying()
            Response.Success(client.results)
        } catch (e: Exception) {
            Log.e("ERRORR", e.message.toString())
            Response.Error(e.message.toString())
        }
    }
    suspend fun getMovieListTopRated(): Response<List<Result>> {
        return try {
            val client = apiService.getMovieListTopRated()
            Response.Success(client.results)
        } catch (e: Exception) {
            Log.e("ERRORR", e.message.toString())
            Response.Error(e.message.toString())
        }
    }


    suspend fun getDetail(id: Int): Response<ResponseDetail> {
        return try {
            val client = apiService.getMovieDetail(id)
            Response.Success(client)
        } catch (e: Exception) {
            Log.e("ERRORR", e.message.toString())
            Response.Error(e.message.toString())
        }

    }




    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            api:ApiService
        ): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository(api).apply {
                    instance = this
                }
            }
    }

}