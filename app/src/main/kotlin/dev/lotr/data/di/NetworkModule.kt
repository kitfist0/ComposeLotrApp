package dev.lotr.data.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.lotr.data.remote.api.OneApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val MAX_CACHE_SIZE_IN_BYTES = 5 * 1024 * 1024L
    private const val MAX_AGE_IN_HOURS = 12

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        val cacheControl = CacheControl.Builder()
            .maxAge(MAX_AGE_IN_HOURS, TimeUnit.HOURS)
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(Cache(context.cacheDir, MAX_CACHE_SIZE_IN_BYTES))
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
                it.proceed(request)
            }
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
