package dev.lotr.data.remote.api

import dev.lotr.data.remote.model.RemoteBook
import dev.lotr.data.remote.model.RemoteChapter
import retrofit2.http.GET
import retrofit2.http.Path

interface OneApi {

    @GET("/book")
    suspend fun getBooks(): List<RemoteBook>

    @GET("/book/{id}/chapter")
    suspend fun getBookChapters(@Path("id") bookId: String): List<RemoteChapter>

    companion object {
        const val BASE_URL = "https://the-one-api.dev/v2"
    }
}