package com.mabrouk.mohamed.topmusicalbums.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mabrouk.mohamed.topmusicalbums.presentation.albumDetails.AlbumsDetailsScreen
import com.mabrouk.mohamed.topmusicalbums.presentation.albumDetails.AlbumDetailsViewModel
import com.mabrouk.mohamed.topmusicalbums.presentation.home.AlbumsListScreen
import com.mabrouk.mohamed.topmusicalbums.presentation.home.AlbumsListViewModel

@Composable
fun TopAlbumsScreen(
    albumsListViewModel: AlbumsListViewModel,
    albumDetailsViewModel: AlbumDetailsViewModel,
    onItunesButtonClick: (String) -> Unit
) {
    val navController = rememberNavController()

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopAlbumsScreens.ALBUMS_LIST.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = TopAlbumsScreens.ALBUMS_LIST.name) {
                AlbumsListScreen(albumsListViewModel) {
                    it?.let {
                        navController.navigate("${TopAlbumsScreens.ALBUM_DETAILS.name}/$it")
                    }
                }
            }

            composable(route = "${TopAlbumsScreens.ALBUM_DETAILS.name}/{albumId}") { backStackEntry ->
                val albumId = backStackEntry.arguments?.getString("albumId")?.toLong()
                AlbumsDetailsScreen(navController, albumDetailsViewModel, albumId){
                    onItunesButtonClick(it)
                }
            }
        }
    }
}


enum class TopAlbumsScreens {
    ALBUMS_LIST,
    ALBUM_DETAILS
}