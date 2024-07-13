package com.mabrouk.mohamed.topmusicalbums.data.remote.di

import android.content.Context
import com.mabrouk.mohamed.topmusicalbums.data.remote.AlbumsApi
import com.mabrouk.mohamed.topmusicalbums.data.remote.AlbumsRemoteDataSource
import com.mabrouk.mohamed.topmusicalbums.data.remote.Params.BASE_URL
import com.mabrouk.mohamed.topmusicalbums.data.repository.AlbumsRepositoryImpl
import com.mabrouk.mohamed.topmusicalbums.domain.repository.AlbumsRepository
import com.mabrouk.mohamed.topmusicalbums.utils.ErrorResolver
import com.mabrouk.mohamed.topmusicalbums.utils.NetworkState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): AlbumsApi {
        return retrofit.create(AlbumsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkState(context: Context): NetworkState {
        return NetworkState(context)
    }

    @Provides
    @Singleton
    fun provideAlbumsRepository(
        networkState: NetworkState,
        albumsApi: AlbumsApi,
        ioDispatcher: CoroutineDispatcher
    ): AlbumsRepository = AlbumsRepositoryImpl(
        AlbumsRemoteDataSource(networkState, albumsApi),
        ioDispatcher
    )


    @Provides
    @Singleton
    fun provideErrorResolver(context: Context): ErrorResolver {
        return ErrorResolver(context)
    }

    private const val READ_TIMEOUT = 30
    private const val WRITE_TIMEOUT = 30
    private const val CONNECTION_TIMEOUT = 10
}