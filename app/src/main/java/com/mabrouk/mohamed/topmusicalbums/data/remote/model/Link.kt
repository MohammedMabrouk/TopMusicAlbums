package com.mabrouk.mohamed.topmusicalbums.data.remote.model


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("self")
    val self: String
)