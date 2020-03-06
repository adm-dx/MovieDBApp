package com.romanmiller.moviedbapp.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.romanmiller.moviedbapp.data.api.TheMovieDBInterface
import com.romanmiller.moviedbapp.data.repository.MovieDetailsNetworkDataSource
import com.romanmiller.moviedbapp.data.repository.NetworkState
import com.romanmiller.moviedbapp.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MovieDetails> {
        movieDetailsNetworkDataSource =
            MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}