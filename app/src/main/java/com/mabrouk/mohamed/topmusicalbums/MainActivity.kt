package com.mabrouk.mohamed.topmusicalbums

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mabrouk.mohamed.topmusicalbums.screens.AlbumsListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumsListScreen()
        }
    }
}