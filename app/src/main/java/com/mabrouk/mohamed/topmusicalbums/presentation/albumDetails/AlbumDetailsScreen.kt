package com.mabrouk.mohamed.topmusicalbums.presentation.albumDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mabrouk.mohamed.topmusicalbums.R
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.ExplicitBadge
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.GenreItem
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.InfoItem

@Composable
fun AlbumsDetailsScreen(
    navController: NavHostController,
    viewModel: AlbumDetailsViewModel,
    albumId: Long?,
    onItunesButtonClick: (String) -> Unit
) {
    val albumDetailsOutCome by viewModel.albumDetailsOutcome.collectAsState()

    LaunchedEffect(Unit) {
        albumId?.let {
            viewModel.getAlbumDetails(albumId)
        }
    }

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
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
            when (albumDetailsOutCome) {
                is Outcome.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            (albumDetailsOutCome as Outcome.Error<AlbumItem>).exception.message
                                ?: stringResource(id = R.string.network_error),
                            color = colorResource(id = R.color.purple_500),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                        Button(
                            onClick = { albumId?.let { viewModel.getAlbumDetails(albumId) } },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = stringResource(id = R.string.try_again))
                        }
                    }
                }

                is Outcome.Progress -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Outcome.Success -> {
                    AlbumDetailsSection(
                        (albumDetailsOutCome as Outcome.Success<AlbumItem>).data,
                        modifier = Modifier.weight(1.0f)
                    )
                    Button(
                        onClick = {
                            (albumDetailsOutCome as Outcome.Success<AlbumItem>).data.albumUrl?.let {
                                onItunesButtonClick(it)
                            }
                        },
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

                else -> {}
            }
        }
    }
}

//@Preview
//@Composable
//fun AlbumsDetailsScreenPreview() {
//    AlbumsDetailsScreen()
//}

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
        album.genres.let { genreList ->
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