package com.mabrouk.mohamed.topmusicalbums.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mabrouk.mohamed.topmusicalbums.R
import com.mabrouk.mohamed.topmusicalbums.data.mapper.toDomain
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AlbumListItem(
    albumItem: AlbumItem,
    onAlbumClick: (AlbumItem) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(270.dp)
            .clickable { onAlbumClick(albumItem) },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = colorResource(id = R.color.white),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            albumItem.albumImageUrl?.let {
                if (it.isNotEmpty()) {
                    Box(
                        modifier = Modifier.size(180.dp)
                    ) {
                        GlideImage(
                            model = albumItem.albumImageUrl,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.FillBounds,
                        )
                        if (albumItem.isExplicit)
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

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = albumItem.name ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = albumItem.artistName ?: "",
                fontSize = 14.sp
            )
        }
    }
}

class ExplicitPreviewParameter : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}

@Composable
@Preview
fun AlbumListItemPreview(
    @PreviewParameter(ExplicitPreviewParameter::class) showExplicit: Boolean
) {
    AlbumListItem(
        AlbumItem().apply {
            id = 1755022177
            name = "The Death of Slim Shady \n(Coup De Grâce)"
            artistName = "Eminem"
            albumImageUrl =
                "https://is1-ssl.mzstatic.com/image/thumb/Music221/v4/8b/2c/ce/8b2cced1-ef53-ae9f-df26-5c5d8ad0009e/24UMGIM70968.rgb.jpg/100x100bb.jpg"
            genres = realmListOf("Hip-Hop/Rap", "Music")
            releaseDate = "2024-07-12"
            isExplicit = showExplicit
            albumUrl =
                "https://music.apple.com/us/album/the-death-of-slim-shady-coup-de-gr%C3%A2ce/1755022177"
        },
        {}
    )
}