package com.mabrouk.mohamed.topmusicalbums.app.di

import android.content.Context
import com.mabrouk.mohamed.topmusicalbums.app.TopAlbumsApp
import com.mabrouk.mohamed.topmusicalbums.domain.repository.AlbumsRepository
import com.mabrouk.mohamed.topmusicalbums.domain.usecase.GetTopAlbumsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): TopAlbumsApp {
        return app as TopAlbumsApp
    }

    @Provides
    @Singleton
    fun provideContext(application: TopAlbumsApp): Context {
        return application.applicationContext
    }


    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGetTopAlbumsUseCase(
        albumsRepository: AlbumsRepository
    ) = GetTopAlbumsUseCase(albumsRepository)
}