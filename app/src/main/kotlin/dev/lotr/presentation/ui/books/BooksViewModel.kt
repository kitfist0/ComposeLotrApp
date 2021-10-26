package dev.lotr.presentation.ui.books

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Book
import dev.lotr.domain.usecase.GetBooksUseCase
import dev.lotr.presentation.navigation.NavDest
import dev.lotr.presentation.navigation.NavManager
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

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> get() = _books.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = getBooksUseCase.invoke(null)) {
                is Result.Error -> Log.d(TAG, "Error: ${result.message}")
                is Result.Success -> _books.value = result.data
            }
        }
    }

    fun onBookClicked(bookId: String) {
        navManager.navigate(NavDest.CHAPTERS, bookId)
    }

    companion object {
        private const val TAG = "BOOKS_VIEW_MODEL"
    }
}
