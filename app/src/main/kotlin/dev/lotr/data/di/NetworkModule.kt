package dev.lotr.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.lotr.data.remote.api.OneApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        val format = Json { ignoreUnknownKeys = true }
        val mediaType = "application/json".toMediaType()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(format.asConverterFactory(mediaType))
    }

    @Provides
    @Singleton
    fun provideOneApi(retrofit: Retrofit.Builder): OneApi {
        return retrofit.baseUrl(OneApi.BASE_URL).build().create(OneApi::class.java)
    }
}
