package dev.lotr.data.repository

import dev.lotr.data.remote.api.OneApi
import dev.lotr.data.remote.model.BookChaptersResponse.Companion.toDomainModel
import dev.lotr.data.remote.model.BooksResponse.Companion.toDomainModel
import dev.lotr.domain.model.Book
import dev.lotr.domain.model.Chapter
import dev.lotr.domain.repository.BookAndChapterRepository
import javax.inject.Inject

class BookAndChapterRepositoryImpl @Inject constructor(
    private val oneApi: OneApi,
) : BookAndChapterRepository {

    override suspend fun getBooks(): List<Book> {
        return oneApi.getBooks().toDomainModel()
    }

    override suspend fun getBookChapters(bookId: String): List<Chapter> {
        return oneApi.getBookChapters(bookId).toDomainModel(bookId)
    }
}
