package com.truongdc.android.base.data.remote.provides

import com.truongdc.android.base.data.remote.api.ApiService
import retrofit2.Retrofit

object ApiServiceProvider {

    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
