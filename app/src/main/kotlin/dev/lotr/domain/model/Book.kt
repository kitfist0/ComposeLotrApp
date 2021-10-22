package dev.lotr.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("_id")
    val id: String,
    val name: String,
)
