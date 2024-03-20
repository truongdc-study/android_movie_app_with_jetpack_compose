package com.truongdc.android.core.repository.movie

import com.truongdc.android.core.base.ExecuteResult
import com.truongdc.android.core.repository.MovieRepository
import com.truongdc.android.core.source.MovieDataSource
import com.truongdc.android.core.di.annotations.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieDataSource.Remote,
    @IoDispatcher dispatcherIO: CoroutineDispatcher
) : ExecuteResult(dispatcherIO), MovieRepository {

    override suspend fun getMovies() = withResultContext {
        remote.getMovies().data
    }

    override suspend fun getDetailMovies(movieId: Int) = withResultContext {
        remote.getMovieDetail(movieId)
    }

}