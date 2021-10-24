package dev.lotr.data.remote.model

import dev.lotr.domain.model.Book
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteBook(
    @SerialName("_id")
    val id: String,
    val name: String,
) {
    companion object {
        fun RemoteBook.toDomainModel(): Book {
            return Book(
                id = id,
                name = name,
            )
        }
    }
}
