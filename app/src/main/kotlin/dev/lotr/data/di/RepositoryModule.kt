package dev.lotr.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.lotr.data.repository.BookAndChapterRepositoryImpl
import dev.lotr.domain.repository.BookAndChapterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: BookAndChapterRepositoryImpl): BookAndChapterRepository
}
