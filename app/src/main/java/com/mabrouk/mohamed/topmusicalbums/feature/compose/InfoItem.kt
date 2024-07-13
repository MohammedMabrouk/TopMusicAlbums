package com.mabrouk.mohamed.topmusicalbums.feature.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mabrouk.mohamed.topmusicalbums.R


@Composable
fun InfoItem(
    title: String,
    value: String
) {
    Column {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier
                .padding(top = 2.dp)
                .padding(start = 4.dp),
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.dark_blue)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InfoItemPreview() {
    InfoItem("Artist", "Eminem")
}