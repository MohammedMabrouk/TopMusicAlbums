package com.mabrouk.mohamed.topmusicalbums.presentation.albumDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mabrouk.mohamed.topmusicalbums.R
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.ExplicitBadge
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.GenreItem
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.InfoItem
import io.realm.kotlin.ext.realmListOf

//
//val album = AlbumItem(
//    _id = 1755022177,
//    name = "The Death of Slim Shady (Coup De Grâce)",
//    artistName = "Eminem",
//    albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/8b/2c/ce/8b2cced1-ef53-ae9f-df26-5c5d8ad0009e/24UMGIM70968.rgb.jpg/100x100bb.jpg",
//    genres = realmListOf("Hip-Hop/Rap", "Music"),
//    releaseDate = "2024-07-12",
//    isExplicit = true,
//    albumUrl = "https://music.apple.com/us/album/the-death-of-slim-shady-coup-de-gr%C3%A2ce/1755022177"
//)

@Composable
fun AlbumsDetailsScreen() {
    // todo: replace with actual data


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.album_details_lbl))
                },
                backgroundColor = colorResource(id = R.color.purple_700),
                contentColor = colorResource(id = R.color.white),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.round_arrow_back_ios_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.purple_500),
                            colorResource(id = R.color.white)
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            AlbumDetailsSection(
//                album,
//                modifier = Modifier.weight(1.0f)
//            )
            Button(
                onClick = {},
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_itunes),
                        contentDescription = "iTunes Button",
                        tint = colorResource(id = R.color.white),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp),
                    )
                    Text(text = stringResource(id = R.string.show_on_itunes_lbl))
                }
            }
        }
    }
}

@Preview
@Composable
fun AlbumsDetailsScreenPreview() {
    AlbumsDetailsScreen()
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AlbumDetailsSection(
    album: AlbumItem,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            album.albumImageUrl?.let {
                if (it.isNotEmpty()) {
                    Box(
                        modifier = Modifier.size(180.dp)
                    ) {
                        GlideImage(
                            model = it,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillBounds,
                        )
                        if (album.isExplicit)
                            ExplicitBadge(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(6.dp)
                            )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(colorResource(id = R.color.black))
                    ) {}
                }
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = album.name ?: "",
            color = colorResource(id = R.color.dark_blue),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoItem(
            stringResource(id = R.string.artist_lbl),
            album.artistName ?: "",
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoItem(
            stringResource(id = R.string.release_date_lbl),
            album.releaseDate ?: "",
        )
        Spacer(modifier = Modifier.height(12.dp))
        album.genres?.let { genreList ->
            Text(
                text = stringResource(id = R.string.genres_lbl),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .padding(start = 4.dp),
            ) {
                items(genreList) {
                    GenreItem(it)
                }
            }
        }
    }

}