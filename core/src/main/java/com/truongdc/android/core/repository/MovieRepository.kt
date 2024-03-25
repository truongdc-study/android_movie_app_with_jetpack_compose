package com.truongdc.android.core.repository

import androidx.paging.PagingData
import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.base.DataResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): DataResult<Flow<PagingData<Movie>>>

    suspend fun getDetailMovies(movieId: Int): DataResult<Movie>
}