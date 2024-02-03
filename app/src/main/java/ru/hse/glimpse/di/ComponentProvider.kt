package ru.hse.glimpse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.glimpse.screens.entrypoint.di.EntrypointComponent
import ru.hse.glimpse.screens.entrypoint.di.EntrypointModule

@Module
@InstallIn(SingletonComponent::class)
internal class ComponentProvider {

    @Provides
    fun provideMainComponent(): EntrypointComponent {
        return object : EntrypointComponent(), EntrypointModule by EntrypointModule() {}
    }
}
