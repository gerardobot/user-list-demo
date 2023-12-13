package com.gerardo.userlist.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gerardo.userlist.framework.ui.loaders.ImageLoader
import org.koin.androidx.compose.get

@Composable
fun RoundAsyncPicture(
    model: String,
    size: Dp,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    borderSize: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    imageLoader: ImageLoader = get()
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(borderSize, borderColor, CircleShape)
    ) {
        imageLoader.AsyncImage(
            model = model,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
