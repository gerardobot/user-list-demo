package com.gerardo.userlist.navigation

sealed class Route(val name: String) {
    data object UserList : Route("user-list")
    data object UserProfile : Route("user-profile/{${NavArg.UserId.key}}")
    data object NetworkError : Route("network-error")
    data object UnknownError : Route("unknown-error")

    fun getRouteWithArgs(vararg valuePair: Pair<NavArg, String>): String =
        valuePair.fold(name) { acc, (key, value) -> acc.replace("{${key.key}}", value) }
}
