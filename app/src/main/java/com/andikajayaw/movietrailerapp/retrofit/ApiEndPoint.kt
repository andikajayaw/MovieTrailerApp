package com.andikajayaw.movietrailerapp.retrofit

import com.andikajayaw.movietrailerapp.model.detailmovie.DetailResponse
import com.andikajayaw.movietrailerapp.model.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("popular")
    fun getMoviePopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("/{movieId}")
    fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("api_key") api_key: String
    ): Call<DetailResponse>
}