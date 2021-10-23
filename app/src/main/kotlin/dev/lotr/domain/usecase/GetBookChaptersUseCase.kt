package dev.lotr.domain.usecase

import dev.lotr.domain.model.Chapter
import dev.lotr.domain.repository.BookAndChapterRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetBookChaptersUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val bookAndChapterRepository: BookAndChapterRepository,
) : BaseUseCase<String, List<Chapter>>(coroutineDispatcher) {
    override suspend fun execute(parameters: String): List<Chapter> {
        return bookAndChapterRepository.getBookChapters(parameters)
    }
}
