package com.truongdc.android.base.di.module

import com.truongdc.android.base.data.repository.MovieRepository
import com.truongdc.android.base.data.repository.movie.MovieRepositoryImpl
import com.truongdc.android.base.data.MovieDataSource
import com.truongdc.android.base.di.annotations.IoDispatcher
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