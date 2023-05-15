package com.andi.gmovie.network

import com.andi.gmovie.model.response.ResponseDetail
import com.andi.gmovie.model.response.ResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/upcoming?api_key=a68bd20074fdd9c9d53697376998cabd")
    suspend fun getMovieListUpcoming():ResponseNetwork
    @GET("movie/popular?api_key=a68bd20074fdd9c9d53697376998cabd")
    suspend fun getMovieListPopular():ResponseNetwork
    @GET("movie/top_rated?api_key=a68bd20074fdd9c9d53697376998cabd")
    suspend fun getMovieListTopRated():ResponseNetwork
    @GET("movie/now_playing?api_key=a68bd20074fdd9c9d53697376998cabd")
    suspend fun getMovieListNowPlaying():ResponseNetwork

    @GET("movie/{id}?api_key=a68bd20074fdd9c9d53697376998cabd")
    suspend fun getMovieDetail(
        @Path("id") id:Int
    ):ResponseDetail
}