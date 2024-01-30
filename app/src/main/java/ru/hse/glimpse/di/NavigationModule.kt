package ru.hse.glimpse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.common_navigation.NavigationScreenModule

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {

    @Provides
    fun provideNavigationScreenModule(): NavigationScreenModule {
        return NavigationScreenModule()
    }
}
