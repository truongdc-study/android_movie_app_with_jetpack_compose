package com.truongdc.android.core.source

import androidx.paging.PagingSource
import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.source.remote.reponses.BaseResponse

interface MovieDataSource {
    interface Local {

    }

    interface Remote {
        suspend fun getMovies(apiKey: String, pageNumber: Int): BaseResponse<List<Movie>>

        suspend fun getMovieDetail(movieId: Int): Movie

    }
}