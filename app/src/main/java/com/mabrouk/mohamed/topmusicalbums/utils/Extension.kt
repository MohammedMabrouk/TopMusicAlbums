package com.mabrouk.mohamed.topmusicalbums.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.mabrouk.mohamed.topmusicalbums.R

fun Activity.openWebPage(url: String) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url),
            )
        )
    } catch (e: Exception) {
        Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_SHORT).show()
    }

}