package dev.lotr.data.remote.model

import dev.lotr.domain.model.Chapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteChapter(
    @SerialName("_id")
    val id: String,
    @SerialName("chapterName")
    val name: String,
    @SerialName("book")
    val bookId: String?,
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
