package dev.lotr.domain.usecase

import dev.lotr.domain.di.IoDispatcher
import dev.lotr.domain.model.Book
import dev.lotr.domain.repository.BookAndChapterRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val bookAndChapterRepository: BookAndChapterRepository,
) : BaseUseCase<Nothing?, List<Book>>(coroutineDispatcher) {
    override suspend fun execute(parameters: Nothing?): List<Book> {
        return bookAndChapterRepository.getBooks()
    }
}
