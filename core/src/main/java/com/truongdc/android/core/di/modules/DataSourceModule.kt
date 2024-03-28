package com.truongdc.android.core.di.modules

import com.truongdc.android.core.source.MovieDataSource
import com.truongdc.android.core.source.remote.MovieRemoteImpl
import com.truongdc.android.core.source.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun providerMovieRemoteSource(apiService: ApiService): MovieDataSource.Remote {
        return MovieRemoteImpl(apiService)
    }

}