package dev.lotr.domain.usecase

import dev.lotr.domain.di.IoDispatcher
import dev.lotr.domain.model.Chapter
import dev.lotr.domain.repository.BookAndChapterRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBookChaptersUseCase @Inject constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val bookAndChapterRepository: BookAndChapterRepository,
) : BaseUseCase<String, List<Chapter>>(coroutineDispatcher) {
    override suspend fun execute(parameters: String): List<Chapter> {
        return bookAndChapterRepository.getBookChapters(parameters)
    }
}
