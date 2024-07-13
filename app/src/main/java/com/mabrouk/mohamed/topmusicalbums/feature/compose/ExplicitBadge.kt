package com.mabrouk.mohamed.topmusicalbums.feature.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mabrouk.mohamed.topmusicalbums.R

@Composable
fun ExplicitBadge(modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .background(colorResource(id = R.color.black))
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .padding(vertical = 2.dp),
            text = "E",
            color = colorResource(id = R.color.white),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun ExplicitBadgePreview() {
    ExplicitBadge(Modifier)
}