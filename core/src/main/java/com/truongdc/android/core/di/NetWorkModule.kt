package com.truongdc.android.core.di

import com.squareup.moshi.Moshi
import com.truongdc.android.base.core.BuildConfig
import com.truongdc.android.core.source.remote.api.ApiService
import com.truongdc.android.core.source.remote.provides.ConverterFactoryProvider
import com.truongdc.android.core.source.remote.provides.MoshiBuilderProvider
import com.truongdc.android.core.source.remote.provides.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    fun providerBaseUrl(): String {
        return BuildConfig.BASE_API_URL
    }

    @Provides
    fun providerMoshi(): Moshi {
        return MoshiBuilderProvider.moshiBuilder.build()
    }

    @Provides
    fun providerMoshiConverterFactory(moshi: Moshi): Converter.Factory {
        return ConverterFactoryProvider.getMoshiConverterFactory(moshi)
    }

    @Provides
    fun providerRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return RetrofitProvider.getRetrofitBuilder(
            baseUrl = baseUrl,
            okHttpClient = okHttpClient,
            converterFactory = converterFactory
        ).build()
    }

    @Provides
    fun providerApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}
