package com.gerardo.userlist.ui.screens.user.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerardo.userlist.domain.entities.user.UserInfo
import com.gerardo.userlist.domain.result.error.DomainError
import com.gerardo.userlist.domain.usecases.user.GetUserInfoUseCase
import com.gerardo.userlist.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserInfoViewModel(
    private val getUserInfo: GetUserInfoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        val userId = savedStateHandle.get<String>(NavArg.UserId.key)
        if (userId.isNullOrEmpty()) {
            _state.update {
                it.copy(error = DomainError.UserInfo.InvalidId())
            }
        } else {
            getUserInfo(userId = userId)
        }
    }

    private fun getUserInfo(userId: String) = viewModelScope.launch {
        getUserInfo(GetUserInfoUseCase.Params(userId))
            .onSuccess { userInfo ->
                _state.update {
                    it.copy(
                        userInfo = userInfo
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        error = error
                    )
                }
            }
    }
}

data class State(
    val userInfo: UserInfo? = null,
    val error: DomainError? = null
) {
    val isLoading = userInfo == null && error == null
}
