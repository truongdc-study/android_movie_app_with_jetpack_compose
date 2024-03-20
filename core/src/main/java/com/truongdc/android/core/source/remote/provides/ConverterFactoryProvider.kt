package com.truongdc.android.core.source.remote.provides

import com.squareup.moshi.Moshi
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

object ConverterFactoryProvider {

    fun getMoshiConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }
}
