package com.mabrouk.mohamed.topmusicalbums.data

import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome

interface AlbumsDataSource {
    suspend fun getTopAlbums(): Outcome<List<AlbumItem>>
}