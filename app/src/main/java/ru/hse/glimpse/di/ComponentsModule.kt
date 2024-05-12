package ru.hse.glimpse.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
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
import ru.hse.glimpse.screens.prompts.di.PromptsComponent
import ru.hse.glimpse.screens.prompts.di.PromptsModule
import ru.hse.glimpse.screens.sign_up.di.SignUpComponent
import ru.hse.glimpse.screens.sign_up.di.SignUpModule
import ru.hse.glimpse.utils.user_info.UserInfoManager

@Module
@InstallIn(SingletonComponent::class)
internal class ComponentsModule {

    @Provides
    fun provideEntrypointComponent(
        profileRepository: ProfileRepository,
        userInfoManager: UserInfoManager,
        promptsRepository: PromptsRepository,
    ): EntrypointComponent {
        return object : EntrypointComponent(),
            EntrypointModule by EntrypointModule(
                profileRepository,
                userInfoManager,
                promptsRepository,
            ) {}
    }

    @Provides
    fun provideInOrUpComponent(): InOrUpComponent {
        return object : InOrUpComponent(), InOrUpModule by InOrUpModule() {}
    }

    @Provides
    fun provideSignUpComponent(
        authRepository: AuthRepository,
        jwtTokenManager: JwtTokenManager,
        userInfoManager: UserInfoManager,
    ): SignUpComponent {
        return object : SignUpComponent(),
            SignUpModule by SignUpModule(authRepository, jwtTokenManager, userInfoManager) {}
    }

    @Provides
    fun provideLogInComponent(
        authRepository: AuthRepository,
        jwtTokenManager: JwtTokenManager,
        userInfoManager: UserInfoManager,
    ): LogInComponent {
        return object : LogInComponent(),
            LogInModule by LogInModule(authRepository, jwtTokenManager, userInfoManager) {}
    }

    @Provides
    fun provideFillProfileComponent(
        profileRepository: ProfileRepository,
        userInfoManager: UserInfoManager,
    ): FillProfileComponent {
        return object : FillProfileComponent(),
            FillProfileModule by FillProfileModule(profileRepository, userInfoManager) {}
    }

    @Provides
    fun provideChatsComponent(): ChatsComponent {
        return object : ChatsComponent(), ChatsModule by ChatsModule() {}
    }

    @Provides
    fun provideMainComponent(): MainComponent {
        return object : MainComponent(), MainModule by MainModule() {}
    }

    @Provides
    fun providePromptsComponent(
        userInfoManager: UserInfoManager,
        promptsRepository: PromptsRepository,
    ): PromptsComponent {
        return object : PromptsComponent(),
            PromptsModule by PromptsModule(userInfoManager, promptsRepository) {}
    }
}
