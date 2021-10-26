package dev.lotr.data.remote.model

import dev.lotr.data.remote.model.RemoteChapter.Companion.toDomainModel
import dev.lotr.domain.model.Chapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookChaptersResponse(
    val docs: List<RemoteChapter>,
) {
    companion object {
        fun BookChaptersResponse.toDomainModel(idOfBook: String? = null): List<Chapter> {
            return docs.map { it.toDomainModel(idOfBook) }
        }
    }
}

@Serializable
data class RemoteChapter(
    @SerialName("_id")
    val id: String,
    @SerialName("chapterName")
    val name: String,
    @SerialName("book")
    val bookId: String? = null,
) {
    companion object {
        fun RemoteChapter.toDomainModel(idOfBook: String? = null): Chapter {
            return Chapter(
                id = id,
                name = name,
                bookId = bookId ?: idOfBook.orEmpty(),
            )
        }
    }
}
