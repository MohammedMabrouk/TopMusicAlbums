package com.mabrouk.mohamed.topmusicalbums.data.remote

import com.mabrouk.mohamed.topmusicalbums.data.AlbumsDataSource
import com.mabrouk.mohamed.topmusicalbums.data.remote.model.GetAlbumsResponse
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.State
import com.mabrouk.mohamed.topmusicalbums.utils.Constants.NETWORK_ERROR
import com.mabrouk.mohamed.topmusicalbums.utils.Constants.NO_DATA
import com.mabrouk.mohamed.topmusicalbums.utils.Constants.NO_INTERNET_CONNECTION
import com.mabrouk.mohamed.topmusicalbums.utils.NetworkState
import java.io.IOException
import javax.inject.Inject

class AlbumsRemoteDataSource @Inject constructor(
    private val networkState: NetworkState,
    private val albumsApi: AlbumsApi,
) : AlbumsDataSource {
    override suspend fun getTopAlbums(): State<List<AlbumItem>> {

        return when (val response = processCall(albumsApi::getTopAlbums)) {
            is List<*> -> {
                State.Success(
                    data = listOf()
                    //todo: map list
                )
            }

            else -> {
                State.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> GetAlbumsResponse): Any? {
        if (!networkState.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            // since API is not providing statusCode, we're checking on the nullability.
            response.feed.results.ifEmpty {
                NO_DATA
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}