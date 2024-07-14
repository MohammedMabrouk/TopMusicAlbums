package com.mabrouk.mohamed.topmusicalbums.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mabrouk.mohamed.topmusicalbums.presentation.albumDetails.AlbumDetailsViewModel
import com.mabrouk.mohamed.topmusicalbums.presentation.home.AlbumsListViewModel
import com.mabrouk.mohamed.topmusicalbums.utils.openWebPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val albumsListViewModel: AlbumsListViewModel by viewModels()
    private val albumDetailsViewModel: AlbumDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopAlbumsScreen(albumsListViewModel, albumDetailsViewModel) {
                openWebPage(it)
            }
        }
    }
}