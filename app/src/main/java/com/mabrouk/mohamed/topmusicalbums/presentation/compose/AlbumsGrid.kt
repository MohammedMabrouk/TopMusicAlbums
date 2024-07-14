package com.mabrouk.mohamed.topmusicalbums.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mabrouk.mohamed.topmusicalbums.R
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import io.realm.kotlin.ext.realmListOf


@Composable
fun AlbumsGrid(
    albumsList: List<AlbumItem>,
    onAlbumClick: (AlbumItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.purple_500),
                        colorResource(id = R.color.white)
                    )
                )
            )
    ) {
        items(albumsList) { item ->
            AlbumListItem(albumItem = item) {
                onAlbumClick(it)
            }
        }
    }
}

@Preview
@Composable
fun AlbumsGridPreview() {

    val fakeList = listOf(
        AlbumItem().apply {
            id = 1755022177
            name = "The Death of Slim Shady (Coup De Grâce)"
            artistName = "Eminem"
            albumImageUrl =
                "https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/8b/2c/ce/8b2cced1-ef53-ae9f-df26-5c5d8ad0009e/24UMGIM70968.rgb.jpg/100x100bb.jpg"
            genres = realmListOf("Hip-Hop/Rap", "Music")
            releaseDate = "2024-07-12"
            isExplicit = true
            albumUrl =
                "https://music.apple.com/us/album/the-death-of-slim-shady-coup-de-gr%C3%A2ce/1755022177"
        },
        AlbumItem().apply {
            id = 1755022177
            name = "The Death of Slim Shady (Coup De Grâce)"
            artistName = "Eminem"
            albumImageUrl =
                "https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/8b/2c/ce/8b2cced1-ef53-ae9f-df26-5c5d8ad0009e/24UMGIM70968.rgb.jpg/100x100bb.jpg"
            genres = realmListOf("Hip-Hop/Rap", "Music")
            releaseDate = "2024-07-12"
            isExplicit = true
            albumUrl =
                "https://music.apple.com/us/album/the-death-of-slim-shady-coup-de-gr%C3%A2ce/1755022177"
        },
    )

    AlbumsGrid(fakeList) {}
}