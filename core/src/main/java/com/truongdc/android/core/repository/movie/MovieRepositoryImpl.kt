package com.truongdc.android.core.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.truongdc.android.core.base.ExecuteResult
import com.truongdc.android.core.repository.MovieRepository
import com.truongdc.android.core.source.MovieDataSource
import com.truongdc.android.core.di.annotations.IoDispatcher
import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.source.remote.reponses.MoviePagingSource
import com.truongdc.android.core.utils.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieDataSource.Remote,
    @IoDispatcher dispatcherIO: CoroutineDispatcher
) : ExecuteResult(dispatcherIO), MovieRepository {
    override suspend fun getMovies() = withResultContext {
        Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(remote)
            }
        ).flow
    }

    override suspend fun getDetailMovies(movieId: Int) = withResultContext {
        remote.getMovieDetail(movieId)
    }

}