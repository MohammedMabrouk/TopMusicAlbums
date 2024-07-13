package com.mabrouk.mohamed.topmusicalbums.data.remote.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("artistId")
    val artistId: String,
    @SerializedName("artistUrl")
    val artistUrl: String,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("url")
    val url: String
)