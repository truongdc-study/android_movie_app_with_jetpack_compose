package com.truongdc.android.core.di

import com.squareup.moshi.Moshi
import com.truongdc.android.core.source.remote.provides.ConverterFactoryProvider
import com.truongdc.android.core.source.remote.api.ApiService
import com.truongdc.android.core.utils.Constant
import com.truongdc.android.core.source.remote.provides.MoshiBuilderProvider
import com.truongdc.android.core.source.remote.provides.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    fun providerBaseUrl(): String {
        return Constant.BASE_URL
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
