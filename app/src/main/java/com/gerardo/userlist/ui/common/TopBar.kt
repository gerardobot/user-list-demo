package com.gerardo.userlist.ui.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gerardo.userlist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    button: TopBarButton,
    actions: List<TopBarButton> = emptyList(),
    color: Color = MaterialTheme.colorScheme.primary,
    title: String = ""
) {
    TopAppBar(
        navigationIcon = { button.Icon(color) },
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = color,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = { actions.forEach { it.Icon(color) } }
    )
}

sealed class TopBarButton(
    @DrawableRes val iconId: Int,
    @StringRes val iconDescription: Int
) {
    data class Menu(override inline val onClick: () -> Unit) :
        TopBarButton(R.drawable.ic_dots, R.string.top_bar_icon_description_menu)

    data class Back(override inline val onClick: () -> Unit) :
        TopBarButton(R.drawable.ic_arrow_back, R.string.top_bar_icon_description_back)

    abstract val onClick: () -> Unit

    @Composable
    fun Icon(color: Color = MaterialTheme.colorScheme.primary) = IconButton(onClick = { this.onClick() }) {
        Image(
            painter = painterResource(id = this.iconId),
            contentDescription = stringResource(this.iconDescription),
            colorFilter = ColorFilter.tint(color)
        )
    }
}
