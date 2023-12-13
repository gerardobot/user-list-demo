package com.gerardo.userlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gerardo.userlist.ui.common.TopBarButton
import com.gerardo.userlist.ui.screens.user.list.UserListScreen
import com.gerardo.userlist.ui.screens.user.profile.UserProfileScreen

@Composable
fun Navigation(
    appState: AppState = rememberAppState()
) {
    with(appState) {
        NavHost(
            navController = navController,
            startDestination = Route.UserList.name
        ) {
            composable(route = Route.UserList.name) {
                UserListScreen(
                    topBarButton = TopBarButton.Menu { onMenuClick() },
                    onUserSelected = {
                        navController.navigate(
                            Route.UserProfile.getRouteWithArgs(NavArg.UserId to it)
                        )
                    }
                )
            }

            composable(
                route = Route.UserProfile.name,
                arguments = listOf(
                    NavArg.UserId.toNavArgument()
                )
            ) {
                UserProfileScreen(
                    topBarButton = TopBarButton.Back {
                        navController.popBackStack()
                    },
                    topBarActions = listOf(
                        TopBarButton.Menu { onMenuClick() }
                    )
                )
            }

            composable(
                route = Route.NetworkError.name
            ) {
            }

            composable(
                route = Route.UnknownError.name
            ) {
            }
        }
    }
}

sealed class NavArg(val key: String, private val navType: NavType<*>) {
    data object UserId : NavArg("user-id", NavType.StringType)

    fun toNavArgument() = navArgument(key) { type = navType }
}
