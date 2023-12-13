@file:JvmName("UserDetailKt")

package com.gerardo.userlist.ui.screens.user.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gerardo.userlist.R
import com.gerardo.userlist.domain.entities.user.UserInfo
import com.gerardo.userlist.ui.common.RoundAsyncPicture
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun UserProfile(
    userInfo: UserInfo,
    modifier: Modifier = Modifier
) = Column(modifier = modifier.padding(bottom = 32.dp)) {
    Image(
        painter = painterResource(id = R.drawable.img_palm_trees),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(198.dp),
        contentScale = ContentScale.Crop
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        RoundAsyncPicture(
            modifier = Modifier
                .offset(y = (-77 / 2).dp),
            model = userInfo.profilePic,
            size = 77.dp,
            borderSize = 3.dp,
            borderColor = Color.White,
            contentDescription = stringResource(
                R.string.user_profile_pic_description,
                userInfo.fullName
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_pencil),
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
    }
    with(userInfo) {
        UserProfileField(
            icon = R.drawable.ic_user,
            label = stringResource(id = R.string.user_profile_label_name),
            value = fullName
        )
        UserProfileField(
            icon = R.drawable.ic_mail,
            label = stringResource(id = R.string.user_profile_label_email),
            value = email
        )
        UserProfileField(
            icon = R.drawable.ic_gender,
            label = stringResource(id = R.string.user_profile_label_gender),
            value = gender.toLocalGender()
        )
        UserProfileField(
            icon = R.drawable.ic_calendar,
            label = stringResource(id = R.string.user_profile_label_registration_date),
            value = registrationDate?.toFormattedString().orEmpty()
        )
        UserProfileField(
            icon = R.drawable.ic_phone,
            label = stringResource(id = R.string.user_profile_label_phone),
            value = phone.orEmpty()
        )
    }
}

@Composable
private fun String.toLocalGender() = stringResource(
    id = when (this) {
        "female" -> R.string.user_profile_gender_female_es
        "male" -> R.string.user_profile_gender_male_es
        else -> R.string.user_profile_gender_other_es
    }
)

private fun LocalDate.toFormattedString() = this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

@Preview(showBackground = true)
@Composable
fun PreviewUserDetail() {
    UserProfile(
        UserInfo(
            profilePic = "https://avatars.githubusercontent.com/u/7422227",
            fullName = "Gerardo Gómez",
            email = "gerardo@mimail.com",
            gender = "male",
            registrationDate = null,
            phone = "1234567890"
        )
    )
}
