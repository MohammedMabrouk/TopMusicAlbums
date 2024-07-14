package com.mabrouk.mohamed.topmusicalbums.data.remote

import com.mabrouk.mohamed.topmusicalbums.data.remote.Params.COUNTRY_CODE
import com.mabrouk.mohamed.topmusicalbums.data.remote.Params.COUNT_LIMIT
import com.mabrouk.mohamed.topmusicalbums.data.remote.model.GetAlbumsResponse
import retrofit2.http.GET

interface AlbumsApi {

    @GET("${COUNTRY_CODE}/music/most-played/${COUNT_LIMIT}/albums.json")
    suspend fun getTopAlbums(): GetAlbumsResponse

}