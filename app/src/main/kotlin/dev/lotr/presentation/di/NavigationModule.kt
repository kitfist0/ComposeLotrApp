package dev.lotr.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.lotr.presentation.navigation.NavManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun providesNavigationManager(): NavManager {
        return NavManager()
    }
}
