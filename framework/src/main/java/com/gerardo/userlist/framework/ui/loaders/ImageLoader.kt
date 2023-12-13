package com.gerardo.userlist.framework.ui.loaders

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

interface ImageLoader {
    @Composable
    fun AsyncImage(
        model: String,
        contentDescription: String?,
        modifier: Modifier,
        contentScale: ContentScale?
    )

    @Composable
    fun Gif(data: Any, contentDescription: String?, modifier: Modifier)
}
