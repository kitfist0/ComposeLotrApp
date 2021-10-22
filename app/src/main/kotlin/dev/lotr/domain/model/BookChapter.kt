package dev.lotr.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookChapter(
    @SerialName("_id")
    val id: String,
    val chapterName: String,
    @SerialName("book")
    val bookId: String,
)
