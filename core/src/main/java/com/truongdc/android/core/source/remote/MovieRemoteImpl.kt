package com.truongdc.android.core.source.remote

import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.source.MovieDataSource
import com.truongdc.android.core.source.remote.api.ApiService
import com.truongdc.android.core.source.remote.reponses.BaseResponse
import com.truongdc.android.core.utils.Constants

class MovieRemoteImpl(private val apiService: ApiService) : MovieDataSource.Remote {

    override suspend fun getMovies(): BaseResponse<List<Movie>> {
        return apiService.getTopRateMovies(apiKey = Constants.BASE_API_KEY)
    }

    override suspend fun getMovieDetail(movieId: Int): Movie {
        return  apiService.getMovieDetails(movieId = movieId, apiKey =  Constants.BASE_API_KEY)
    }

}