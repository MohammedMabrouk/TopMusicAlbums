package com.mabrouk.mohamed.topmusicalbums.data.mapper

import com.mabrouk.mohamed.topmusicalbums.data.remote.model.Genre
import com.mabrouk.mohamed.topmusicalbums.data.remote.model.Result
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem

fun Result.toDomain(): AlbumItem =
    AlbumItem(
        id = id,
        name = name,
        artistName = artistName,
        albumImageUrl = artworkUrl100,
        genres = genres.map { genre -> genre.toDomain() },
        releaseDate = releaseDate,
        isExplicit = contentAdvisoryRating == "Explict",
        albumUrl = url
    )


fun Genre.toDomain(): String = name
