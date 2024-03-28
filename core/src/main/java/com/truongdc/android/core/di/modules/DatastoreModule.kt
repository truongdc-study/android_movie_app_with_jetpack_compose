package com.truongdc.android.core.di.modules

import com.truongdc.android.core.source.local.datastores.PreferencesDataStore
import com.truongdc.android.core.source.local.datastores.PreferencesDataStoreImpl
import com.truongdc.android.core.source.local.datastores.UserDataStore
import com.truongdc.android.core.source.local.datastores.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

    @Binds
    @Singleton
    fun providerUserDatastore(impl: UserDataStoreImpl): UserDataStore

    @Binds
    @Singleton
    fun providesPreferencesDataStore(impl: PreferencesDataStoreImpl): PreferencesDataStore

}