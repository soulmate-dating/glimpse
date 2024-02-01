package ru.hse.glimpse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.common_navigation.NavigationScreenModule
import ru.hse.glimpse.entrypoint.di.EntrypointComponent

@Module
@InstallIn(SingletonComponent::class)
internal class ComponentProvider {

    @Provides
    fun provideMainComponent(navigationScreenModule: NavigationScreenModule): EntrypointComponent {
        return EntrypointComponent(navigationScreenModule)
    }
}
