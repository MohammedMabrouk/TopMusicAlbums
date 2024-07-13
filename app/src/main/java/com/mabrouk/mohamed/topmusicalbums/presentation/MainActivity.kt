package com.mabrouk.mohamed.topmusicalbums.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mabrouk.mohamed.topmusicalbums.presentation.home.AlbumsListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlbumsListScreen()
        }
    }
}