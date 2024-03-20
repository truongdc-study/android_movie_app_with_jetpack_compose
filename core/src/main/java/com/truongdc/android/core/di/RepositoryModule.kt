package com.truongdc.android.core.di

import com.truongdc.android.core.repository.MovieRepository
import com.truongdc.android.core.repository.movie.MovieRepositoryImpl
import com.truongdc.android.core.source.MovieDataSource
import com.truongdc.android.core.di.annotations.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerMovieRepository(
        remote: MovieDataSource.Remote,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): MovieRepository {
        return MovieRepositoryImpl(remote, ioDispatcher)
    }
}