package com.mabrouk.mohamed.topmusicalbums.domain.usecase

import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.domain.repository.AlbumsRepository
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumDetailsUseCase @Inject constructor(
    private val albumsRepository: AlbumsRepository
) {
    fun invoke(albumId: Long): Flow<Outcome<AlbumItem>> =
        albumsRepository.getAlbumDetails(albumId)

}