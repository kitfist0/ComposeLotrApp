package dev.lotr.data.remote.api

import dev.lotr.data.remote.model.BookChaptersResponse
import dev.lotr.data.remote.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OneApi {
    @GET("book")
    suspend fun getBooks(): BooksResponse

    @GET("book/{id}/chapter")
    suspend fun getBookChapters(@Path("id") bookId: String): BookChaptersResponse

    companion object {
        const val BASE_URL = "https://the-one-api.dev/v2/"
    }
}