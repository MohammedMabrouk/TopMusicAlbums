package com.mabrouk.mohamed.topmusicalbums.data.repository

import com.mabrouk.mohamed.topmusicalbums.data.local.AlbumsLocalDataSource
import com.mabrouk.mohamed.topmusicalbums.data.remote.AlbumsRemoteDataSource
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.domain.repository.AlbumsRepository
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val albumsRemoteDataSource: AlbumsRemoteDataSource,
    private val albumsLocalDataSource: AlbumsLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : AlbumsRepository {
    override fun getTopAlbums(): Flow<Outcome<List<AlbumItem>>> {
        return flow {
            emit(Outcome.loading())
            val response = albumsRemoteDataSource.getTopAlbums()
            if (response is Outcome.Success) {
                emit(response)
                albumsLocalDataSource.saveAlbums(response.data)
            } else if (response is Outcome.Error) {
                emit(albumsLocalDataSource.getTopAlbums())
            }

        }.flowOn(ioDispatcher)
    }

    override fun getAlbumDetails(albumId: Long): Flow<Outcome<AlbumItem>> {
        return flow {
            emit(Outcome.loading())
            emit(albumsLocalDataSource.getAlbumById(albumId))
        }.flowOn(ioDispatcher)
    }
}