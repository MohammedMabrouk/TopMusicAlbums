package com.mabrouk.mohamed.topmusicalbums.data.local.di

import com.mabrouk.mohamed.topmusicalbums.data.local.database.RealmDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {

    @Provides
    @Singleton
    fun provideRealmDatabase() = RealmDataBase()
}