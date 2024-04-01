package com.truongdc.android.base.di.module

import com.squareup.moshi.Moshi
import com.truongdc.android.base.data.remote.provides.MoshiBuilderProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MoshiModule {

    @Provides
    fun provideMoshi(): Moshi = MoshiBuilderProvider.moshiBuilder.build()
}