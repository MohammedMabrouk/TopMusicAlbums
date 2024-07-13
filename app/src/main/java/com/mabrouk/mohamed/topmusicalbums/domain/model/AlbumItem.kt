package com.mabrouk.mohamed.topmusicalbums.domain.model

data class AlbumItem(
    val id: String,
    val name: String,
    val artistName: String,
    val albumImageUrl: String,
    val genres: List<String>,
    val releaseDate: String,
    val isExplicit: Boolean,
    val albumUrl: String
)