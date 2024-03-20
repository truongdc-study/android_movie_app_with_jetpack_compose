package com.truongdc.android.core.source.remote

import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.source.MovieDataSource
import com.truongdc.android.core.source.remote.api.ApiService
import com.truongdc.android.core.source.remote.reponses.BaseResponse
import com.truongdc.android.core.utils.Constant

class MovieRemoteImpl(private val apiService: ApiService) : MovieDataSource.Remote {

    override suspend fun getMovies(): BaseResponse<List<Movie>> {
        return apiService.getTopRateMovies(apiKey = Constant.BASE_API_KEY)
    }

    override suspend fun getMovieDetail(movieId: Int): Movie {
        return  apiService.getMovieDetails(movieId = movieId, apiKey =  Constant.BASE_API_KEY)
    }

}