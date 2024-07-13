package com.mabrouk.mohamed.topmusicalbums.data

import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.State

interface AlbumsDataSource {
    suspend fun getTopAlbums(): State<List<AlbumItem>>
}