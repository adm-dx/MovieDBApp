package com.romanmiller.moviedbapp.data.api

import com.romanmiller.moviedbapp.data.vo.MovieDetails
import com.romanmiller.moviedbapp.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/popular?api_key=ebf0381b28ba8fa67f2d1d133098e760
    // https://api.themoviedb.org/3/movie/475303?api_key=ebf0381b28ba8fa67f2d1d133098e760
    // https://api.themoviedb.org/3/

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}