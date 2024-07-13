package com.mabrouk.mohamed.topmusicalbums.data.remote.model


import com.google.gson.annotations.SerializedName

data class GetAlbumsResponse(
    @SerializedName("feed")
    val feed: Feed
)