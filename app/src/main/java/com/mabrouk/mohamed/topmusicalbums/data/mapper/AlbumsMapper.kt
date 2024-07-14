package com.mabrouk.mohamed.topmusicalbums.data.mapper

import com.mabrouk.mohamed.topmusicalbums.data.remote.model.Genre
import com.mabrouk.mohamed.topmusicalbums.data.remote.model.Result
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import io.realm.kotlin.ext.toRealmList

fun Result.toDomain(): AlbumItem =
    AlbumItem().apply {
        id = this@toDomain.id.toLongOrNull() ?: 0
        name = this@toDomain.name
        artistName = this@toDomain.artistName
        albumImageUrl = this@toDomain.artworkUrl100
        genres = this@toDomain.genres.map { genre -> genre.toDomain() }.toRealmList()
        releaseDate = this@toDomain.releaseDate
        isExplicit = this@toDomain.contentAdvisoryRating == "Explict"
        albumUrl = this@toDomain.url
    }


fun Genre.toDomain(): String = this@toDomain.name
