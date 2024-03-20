package com.truongdc.android.core.source

import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.source.remote.reponses.BaseResponse

interface MovieDataSource {
    interface Local {

    }

    interface Remote {
        suspend fun getMovies(): BaseResponse<List<Movie>>

        suspend fun getMovieDetail(movieId: Int): Movie
    }
}