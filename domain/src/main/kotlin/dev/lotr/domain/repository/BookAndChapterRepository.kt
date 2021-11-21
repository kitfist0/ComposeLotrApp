package dev.lotr.domain.repository

import dev.lotr.domain.model.Book
import dev.lotr.domain.model.Chapter

interface BookAndChapterRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBookChapters(bookId: String): List<Chapter>
}
