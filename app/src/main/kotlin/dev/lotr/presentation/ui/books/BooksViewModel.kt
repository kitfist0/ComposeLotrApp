package dev.lotr.presentation.ui.books

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Book
import dev.lotr.domain.usecase.GetBooksUseCase
import dev.lotr.presentation.navigation.NavDest
import dev.lotr.presentation.navigation.NavManager
import dev.lotr.presentation.navigation.toRouteWithParam
import dev.lotr.presentation.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val navManager: NavManager,
) : BaseViewModel<List<Book>>() {

    init {
        fetchData()
    }

    override suspend fun invokeUseCase(): Result<List<Book>> {
        return getBooksUseCase.invoke(null)
    }

    fun onBookClicked(bookId: String) {
        navManager.navigate(NavDest.CHAPTERS.toRouteWithParam(bookId))
    }
}
