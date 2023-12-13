package com.gerardo.userlist.ui.screens.user.profile

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gerardo.userlist.ui.common.TopBar
import com.gerardo.userlist.ui.common.TopBarButton
import org.koin.androidx.compose.getViewModel

@Composable
fun UserProfileScreen(
    topBarButton: TopBarButton,
    topBarActions: List<TopBarButton>,
    viewModel: UserInfoViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopBar(
                button = topBarButton,
                actions = topBarActions,
                title = state.userInfo?.fullName?.uppercase().orEmpty(),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    ) { padding ->
        state.userInfo?.let {
            UserProfile(
                userInfo = it,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .offset(y = -padding.calculateTopPadding())
            )
        } ?: CircularProgressIndicator()
    }
}
