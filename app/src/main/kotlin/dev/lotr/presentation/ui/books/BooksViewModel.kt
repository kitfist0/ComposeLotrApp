package dev.lotr.presentation.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Book
import dev.lotr.domain.usecase.GetBooksUseCase
import dev.lotr.presentation.navigation.NavDest
import dev.lotr.presentation.navigation.NavManager
import dev.lotr.presentation.navigation.toRouteWithParam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val navManager: NavManager,
) : ViewModel() {

    private val _books = MutableStateFlow<Result<List<Book>>>(Result.Loading)
    val books: StateFlow<Result<List<Book>>> = _books.asStateFlow()

    init {
        viewModelScope.launch {
            _books.value = getBooksUseCase.invoke(null)
        }
    }

    fun onBookClicked(bookId: String) {
        navManager.navigate(NavDest.CHAPTERS.toRouteWithParam(bookId))
    }
}
