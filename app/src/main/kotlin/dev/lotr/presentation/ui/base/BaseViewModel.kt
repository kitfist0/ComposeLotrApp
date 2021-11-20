package dev.lotr.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lotr.domain.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<D> : ViewModel() {

    private val mutableStateFlow = MutableStateFlow<Result<D>>(Result.Loading)
    val stateFlow = mutableStateFlow.asStateFlow()

    protected abstract suspend fun invokeUseCase(): Result<D>

    fun fetchData() {
        viewModelScope.launch {
            mutableStateFlow.value = invokeUseCase()
        }
    }
}
