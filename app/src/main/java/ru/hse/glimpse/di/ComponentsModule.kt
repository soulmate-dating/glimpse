package ru.hse.glimpse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.glimpse.screens.entrypoint.di.EntrypointComponent
import ru.hse.glimpse.screens.entrypoint.di.EntrypointModule
import ru.hse.glimpse.screens.in_or_up.di.InOrUpComponent
import ru.hse.glimpse.screens.in_or_up.di.InOrUpModule
import ru.hse.glimpse.screens.sign_up.di.SignUpComponent
import ru.hse.glimpse.screens.sign_up.di.SignUpModule

@Module
@InstallIn(SingletonComponent::class)
internal class ComponentsModule {

    @Provides
    fun provideMainComponent(): EntrypointComponent {
        return object : EntrypointComponent(), EntrypointModule by EntrypointModule() {}
    }

    @Provides
    fun provideInOrUpComponent(): InOrUpComponent {
        return object : InOrUpComponent(), InOrUpModule by InOrUpModule() {}
    }

    @Provides
    fun provideSignUpComponent(): SignUpComponent {
        return object : SignUpComponent(), SignUpModule by SignUpModule() {}
    }
}
