package com.romanmiller.moviedbapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romanmiller.moviedbapp.data.api.TheMovieDBInterface
import com.romanmiller.moviedbapp.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsNetworkDataSource(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadMovieDetailsResponse: LiveData<MovieDetails>
        get() = _downloadMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            apiService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _downloadMovieDetailsResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieDetailsDataSource", it.message)
                })
        } catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message)
        }
    }
}