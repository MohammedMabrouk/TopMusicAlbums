package com.mabrouk.mohamed.topmusicalbums.domain.repository

import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.State
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getTopAlbums(): Flow<State<List<AlbumItem>>>
}