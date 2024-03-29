package ru.hse.glimpse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.glimpse.screens.chats.di.ChatsComponent
import ru.hse.glimpse.screens.chats.di.ChatsModule
import ru.hse.glimpse.screens.entrypoint.di.EntrypointComponent
import ru.hse.glimpse.screens.entrypoint.di.EntrypointModule
import ru.hse.glimpse.screens.fill_profile.di.FillProfileComponent
import ru.hse.glimpse.screens.fill_profile.di.FillProfileModule
import ru.hse.glimpse.screens.in_or_up.di.InOrUpComponent
import ru.hse.glimpse.screens.in_or_up.di.InOrUpModule
import ru.hse.glimpse.screens.log_in.di.LogInComponent
import ru.hse.glimpse.screens.log_in.di.LogInModule
import ru.hse.glimpse.screens.main.di.MainComponent
import ru.hse.glimpse.screens.main.di.MainModule
import ru.hse.glimpse.screens.sign_up.di.SignUpComponent
import ru.hse.glimpse.screens.sign_up.di.SignUpModule

@Module
@InstallIn(SingletonComponent::class)
internal class ComponentsModule {

    @Provides
    fun provideEntrypointComponent(): EntrypointComponent {
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

    @Provides
    fun provideLogInComponent(): LogInComponent {
        return object : LogInComponent(), LogInModule by LogInModule() {}
    }

    @Provides
    fun provideFillProfileComponent(): FillProfileComponent {
        return object : FillProfileComponent(), FillProfileModule by FillProfileModule() {}
    }

    @Provides
    fun provideChatsComponent(): ChatsComponent {
        return object : ChatsComponent(), ChatsModule by ChatsModule() {}
    }

    @Provides
    fun provideMainComponent(): MainComponent {
        return object : MainComponent(), MainModule by MainModule() {}
    }
}
