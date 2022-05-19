package dev.lotr.app.ui.books

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lotr.app.navigation.NavDest
import dev.lotr.app.navigation.NavManager
import dev.lotr.app.navigation.toRouteWithParam
import dev.lotr.app.ui.base.BaseViewModel
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Book
import dev.lotr.domain.usecase.GetBooksUseCase
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
