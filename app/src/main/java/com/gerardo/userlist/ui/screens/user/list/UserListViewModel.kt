package com.gerardo.userlist.ui.screens.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerardo.userlist.domain.entities.user.BasicUserInfo
import com.gerardo.userlist.domain.result.error.DomainError
import com.gerardo.userlist.domain.usecases.user.GetUsersPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUsersPage: GetUsersPageUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getNextUsersPage()
    }

    fun onSearchQueryChanged(query: String) {
        _state.update { it.copy(filter = query) }
    }

    fun onScrollEnd() {
        if (state.value.canGetNextPage) getNextUsersPage()
    }

    private fun getNextUsersPage() = state.value.nextPage?.let {
        viewModelScope.launch {
            _state.update { it.copy(isGettingNextUsersPage = true) }
            getUsersPage(GetUsersPageUseCase.Params(it))
                .onSuccess { page ->
                    _state.update {
                        it.copy(
                            userItems = state.value.userItems + page.items,
                            nextPage = page.nextPage
                        )
                    }
                }
                .onError { error ->
                    if (state.value.userItems.isEmpty()) {
                        _state.update { it.copy(error = error) }
                    }
                }
            _state.update { it.copy(isGettingNextUsersPage = false) }
        }
    }

    data class State(
        val userItems: List<BasicUserInfo> = listOf(),
        val filter: String = "",
        val nextPage: Int? = 1,
        val error: DomainError? = null,
        val isGettingNextUsersPage: Boolean = false
    ) {
        val filteredUserItems: List<BasicUserInfo> = if (filter.isEmpty()) {
            userItems
        } else {
            userItems.filter {
                it.fullName.contains(filter, ignoreCase = true) ||
                    it.email.contains(filter, ignoreCase = true)
            }
        }
        val hasItems: Boolean = userItems.isNotEmpty()
        val isFiltered: Boolean = filter.isNotEmpty()
        val isLoading = userItems.isEmpty() && error == null
        val canGetNextPage = !isLoading && !isGettingNextUsersPage && !isFiltered
    }
}
