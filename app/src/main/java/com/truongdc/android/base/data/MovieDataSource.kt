package com.truongdc.android.base.data

import androidx.paging.PagingSource
import com.truongdc.android.base.model.Movie
import com.truongdc.android.base.data.remote.reponses.BaseResponse

interface MovieDataSource {
    interface Local

    interface Remote {
        suspend fun getMovies(apiKey: String, pageNumber: Int): BaseResponse<List<Movie>>

        suspend fun getMovieDetail(movieId: Int): Movie

    }
}