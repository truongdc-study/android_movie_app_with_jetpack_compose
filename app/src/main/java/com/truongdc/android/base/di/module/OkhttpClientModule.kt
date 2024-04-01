package com.truongdc.android.base.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.truongdc.android.base.data.remote.interceptors.HeaderInterceptor
import com.truongdc.android.base.data.remote.interceptors.TokenAuthenticator
import com.truongdc.android.base.data.remote.provides.OkHttpClientProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class OkhttpClientModule {

    @Provides
    fun providerAuthOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
        authInterceptor: com.truongdc.android.base.data.remote.interceptors.AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator,
    ): OkHttpClient {
        return OkHttpClientProvider.getOkHttpClientBuilder(
            httpLoggingInterceptor = httpLoggingInterceptor,
            chuckerInterceptor = chuckerInterceptor,
            headerInterceptor = headerInterceptor,
            authInterceptor = authInterceptor,
            tokenAuthenticator = tokenAuthenticator,
        ).build()
    }

    @Provides
    fun providerChuckerInterceptor(
        @ApplicationContext context: Context
    ): ChuckerInterceptor {
        return OkHttpClientProvider.getChuckerInterceptor(context)
    }

    @Provides
    fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return OkHttpClientProvider.getHttpLoggingInterceptor()
    }

    @Provides
    fun providerHeaderInterceptor(): HeaderInterceptor {
        return OkHttpClientProvider.getHeaderInterceptor()
    }

}