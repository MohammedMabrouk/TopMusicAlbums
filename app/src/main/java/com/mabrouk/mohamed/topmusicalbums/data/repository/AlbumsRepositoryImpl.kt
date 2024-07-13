package com.mabrouk.mohamed.topmusicalbums.data.repository

import com.mabrouk.mohamed.topmusicalbums.data.remote.AlbumsRemoteDataSource
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.domain.repository.AlbumsRepository
import com.mabrouk.mohamed.topmusicalbums.presentation.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val albumsRemoteDataSource: AlbumsRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : AlbumsRepository {
    override fun getTopAlbums(): Flow<State<List<AlbumItem>>> {
        return flow {
            emit(State.Loading())
            emit(albumsRemoteDataSource.getTopAlbums())
        }.flowOn(ioDispatcher)
    }
}