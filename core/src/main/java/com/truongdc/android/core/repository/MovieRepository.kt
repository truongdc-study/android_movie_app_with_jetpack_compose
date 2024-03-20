package com.truongdc.android.core.repository

import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.base.DataResult

interface MovieRepository {
    suspend fun getMovies(): DataResult<List<Movie>>

    suspend fun getDetailMovies(movieId: Int): DataResult<Movie>
}