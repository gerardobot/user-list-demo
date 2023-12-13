package com.gerardo.userlist.ui.screens.user.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerardo.userlist.R
import com.gerardo.userlist.domain.entities.user.BasicUserInfo
import com.gerardo.userlist.ui.common.RoundAsyncPicture
import com.gerardo.userlist.ui.theme.GreyLight

@Composable
fun UserListItem(
    user: BasicUserInfo,
    onClick: (String) -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .clickable(onClick = { onClick(user.email) })
) {
    RoundAsyncPicture(
        modifier = Modifier.padding(start = 16.dp),
        model = user.profilePic,
        size = 52.dp,
        contentDescription = stringResource(
            R.string.user_profile_pic_description,
            user.fullName
        )
    )

    Column(
        modifier = Modifier
            .padding(start = 16.dp)
            .align(Alignment.CenterVertically)
    ) {
        Row {
            Column {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = user.fullName,
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(fontSize = 16.sp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = user.email,
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 14.sp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_small),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Divider(color = GreyLight, thickness = 1.dp)
    }
}

@Preview
@Composable
fun UserListItemPreview() {
    UserListItem(
        user = BasicUserInfo(
            profilePic = "https://avatars.githubusercontent.com/u/7422227",
            fullName = "Name",
            email = "name@mail.com"
        ),
        onClick = {}
    )
}
