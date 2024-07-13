package com.mabrouk.mohamed.topmusicalbums.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mabrouk.mohamed.topmusicalbums.R
import com.mabrouk.mohamed.topmusicalbums.feature.compose.AlbumsGrid
import com.mabrouk.mohamed.topmusicalbums.feature.compose.InfoSection

@Composable
fun AlbumsListScreen() {
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
            AlbumsGrid()
        }
    }
}


@Preview
@Composable
fun AlbumsListScreenPreview() {
    AlbumsListScreen()
}