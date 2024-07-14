package com.mabrouk.mohamed.topmusicalbums.data.local

import com.mabrouk.mohamed.topmusicalbums.data.AlbumsDataSource
import com.mabrouk.mohamed.topmusicalbums.data.local.database.RealmDataBase
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome
import com.mabrouk.mohamed.topmusicalbums.utils.Constants.NO_DATA
import javax.inject.Inject

class AlbumsLocalDataSource @Inject constructor(
    private val realmDataBase: RealmDataBase
) : AlbumsDataSource {
    override suspend fun getTopAlbums(): Outcome<List<AlbumItem>> {
        return try {
            realmDataBase.getAlbums().let {
                if (it.isEmpty()) {
                    Outcome.Error(Exception(NO_DATA.toString()))
                } else {
                    Outcome.Success(it)
                }
            }
        } catch (e: Exception) {
            Outcome.Error(Exception(NO_DATA.toString()))
        }
    }

    override suspend fun getAlbumById(albumId: Long): Outcome<AlbumItem> {
        realmDataBase.getAlbumById(albumId).let {
            return if (it == null) {
                Outcome.Error(Exception(NO_DATA.toString()))
            } else {
                Outcome.Success(it)
            }
        }
    }

    fun saveAlbums(albums: List<AlbumItem>) {
        realmDataBase.saveAlbums(albums)
    }

}