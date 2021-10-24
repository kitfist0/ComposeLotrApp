package dev.lotr.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.lotr.data.remote.api.OneApi
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
            .apply { addInterceptor(loggingInterceptor) }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().client(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideOneApi(retrofit: Retrofit.Builder): OneApi {
        return retrofit.baseUrl(OneApi.BASE_URL).build().create(OneApi::class.java)
    }
}
