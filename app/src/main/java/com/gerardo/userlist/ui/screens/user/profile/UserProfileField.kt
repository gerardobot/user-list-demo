package com.gerardo.userlist.ui.screens.user.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerardo.userlist.R
import com.gerardo.userlist.ui.theme.Grey
import com.gerardo.userlist.ui.theme.GreyLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileField(
    @DrawableRes icon: Int,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 20.dp)
        )
        Column {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = label,
                style = TextStyle(
                    color = Grey,
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = value,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Divider(color = GreyLight, thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailFieldPreview() {
    UserProfileField(
        icon = R.drawable.ic_user,
        label = "Label",
        value = "Value",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    )
}
