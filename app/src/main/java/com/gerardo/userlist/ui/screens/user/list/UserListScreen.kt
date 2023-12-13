package com.gerardo.userlist.ui.screens.user.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gerardo.userlist.R
import com.gerardo.userlist.ui.common.TopBar
import com.gerardo.userlist.ui.common.TopBarButton
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserListScreen(
    topBarButton: TopBarButton,
    onUserSelected: (String) -> Unit,
    viewModel: UserListViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopBar(
                button = topBarButton,
                title = stringResource(id = R.string.top_bar_title_user_list)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding),
            state = lazyListState
        ) {
            stickyHeader {
                if (state.hasItems) {
                    SearchField(
                        modifier = Modifier.padding(bottom = 24.dp)
                    ) { viewModel.onSearchQueryChanged(it) }
                }
            }
            items(state.filteredUserItems + null) { item ->
                if (item == null) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (!state.isFiltered) CircularProgressIndicator()
                    }
                } else {
                    UserListItem(user = item) {
                        onUserSelected(it)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                if (!state.isGettingNextUsersPage) {
                    LaunchedEffect(true) {
                        viewModel.onScrollEnd()
                    }
                }
            }
        }
    }
}

@Composable
fun SearchField(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { newText ->
            searchText = newText
            onSearch(newText)
        },
        label = { Text(stringResource(id = R.string.user_list_search_field)) },
        singleLine = true,
        modifier = modifier.fillMaxWidth().shadow(elevation = 1.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    UserListScreen(
        topBarButton = TopBarButton.Menu {},
        onUserSelected = {}
    )
}
