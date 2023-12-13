package com.gerardo.userlist.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gerardo.userlist.navigation.Navigation
import com.gerardo.userlist.ui.theme.AppTheme

@Composable
fun MainScreen() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize().statusBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigation()
        }
    }
}
