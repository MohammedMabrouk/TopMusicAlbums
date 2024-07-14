package com.mabrouk.mohamed.topmusicalbums.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mabrouk.mohamed.topmusicalbums.R
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.AlbumsGrid
import com.mabrouk.mohamed.topmusicalbums.presentation.compose.InfoSection

@Composable
fun AlbumsListScreen(
    viewModel: AlbumsListViewModel,
    onAlbumClick: (Long?) -> Unit
) {
    val albumsOutcome by viewModel.albumsOutcome.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.top_music_lbl))
                },
                backgroundColor = colorResource(id = R.color.purple_700),
                contentColor = colorResource(id = R.color.white)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            InfoSection()
            when (albumsOutcome) {
                is Outcome.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            (albumsOutcome as Outcome.Error<List<AlbumItem>>).exception.message
                                ?: stringResource(id = R.string.network_error),
                            color = colorResource(id = R.color.purple_500),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                        Button(
                            onClick = { viewModel.getAlbums() },
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
                    AlbumsGrid((albumsOutcome as Outcome.Success<List<AlbumItem>>).data) {
                        onAlbumClick(it.id)
                    }
                }

                else -> {}
            }
        }
    }
}


//@Preview
//@Composable
//fun AlbumsListScreenPreview() {
//    AlbumsListScreen()
//}