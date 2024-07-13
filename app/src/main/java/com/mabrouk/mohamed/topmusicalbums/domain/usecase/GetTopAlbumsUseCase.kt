package com.mabrouk.mohamed.topmusicalbums.domain.usecase

import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.domain.repository.AlbumsRepository
import com.mabrouk.mohamed.topmusicalbums.presentation.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(
    private val albumsRepository: AlbumsRepository
) {
    fun invoke(): Flow<State<List<AlbumItem>>> =
        albumsRepository.getTopAlbums()

}