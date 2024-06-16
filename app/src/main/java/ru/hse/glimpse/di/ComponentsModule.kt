package ru.hse.glimpse.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.hse.glimpse.network.api.chats.ChatsRepository
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.network.api.reactions.ReactionsRepository
import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.account.di.AccountComponent
import ru.hse.glimpse.screens.account.di.AccountModule
import ru.hse.glimpse.screens.chats.dialog.di.DialogComponent
import ru.hse.glimpse.screens.chats.dialog.di.DialogModule
import ru.hse.glimpse.screens.chats.list_of_chats.di.ChatsComponent
import ru.hse.glimpse.screens.chats.list_of_chats.di.ChatsModule
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
import ru.hse.glimpse.screens.reactions.di.ReactionsComponent
import ru.hse.glimpse.screens.reactions.di.ReactionsModule
import ru.hse.glimpse.screens.sign_up.di.SignUpComponent
import ru.hse.glimpse.screens.sign_up.di.SignUpModule
import ru.hse.glimpse.utils.user_info.UserInfoManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ComponentsModule {

    @Provides
    @Singleton
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
    @Singleton
    fun provideInOrUpComponent(): InOrUpComponent {
        return object : InOrUpComponent(), InOrUpModule by InOrUpModule() {}
    }

    @Provides
    @Singleton
    fun provideSignUpComponent(
        authRepository: AuthRepository,
        jwtTokenManager: JwtTokenManager,
        userInfoManager: UserInfoManager,
    ): SignUpComponent {
        return object : SignUpComponent(),
            SignUpModule by SignUpModule(authRepository, jwtTokenManager, userInfoManager) {}
    }

    @Provides
    @Singleton
    fun provideLogInComponent(
        authRepository: AuthRepository,
        jwtTokenManager: JwtTokenManager,
        userInfoManager: UserInfoManager,
        profileRepository: ProfileRepository,
        promptsRepository: PromptsRepository,
    ): LogInComponent {
        return object : LogInComponent(),
            LogInModule by LogInModule(
                authRepository,
                jwtTokenManager,
                userInfoManager,
                profileRepository,
                promptsRepository,
            ) {}
    }

    @Provides
    @Singleton
    fun provideFillProfileComponent(
        profileRepository: ProfileRepository,
        userInfoManager: UserInfoManager,
    ): FillProfileComponent {
        return object : FillProfileComponent(),
            FillProfileModule by FillProfileModule(profileRepository, userInfoManager) {}
    }

    @Provides
    @Singleton
    fun provideChatsComponent(
        userInfoManager: UserInfoManager,
        chatsRepository: ChatsRepository
    ): ChatsComponent {
        return object : ChatsComponent(),
            ChatsModule by ChatsModule(userInfoManager, chatsRepository) {}
    }

    @Provides
    @Singleton
    fun provideMainComponent(
        userInfoManager: UserInfoManager,
        profileRepository: ProfileRepository,
        reactionsRepository: ReactionsRepository,
    ): MainComponent {
        return object : MainComponent(),
            MainModule by MainModule(userInfoManager, profileRepository, reactionsRepository) {}
    }

    @Provides
    @Singleton
    fun providePromptsComponent(
        userInfoManager: UserInfoManager,
        promptsRepository: PromptsRepository,
        profileRepository: ProfileRepository,
        @ApplicationContext context: Context,
    ): PromptsComponent {
        return object : PromptsComponent(),
            PromptsModule by PromptsModule(
                userInfoManager, promptsRepository,
                profileRepository,
                context
            ) {}
    }

    @Provides
    @Singleton
    fun provideAccountComponent(
        userInfoManager: UserInfoManager,
        profileRepository: ProfileRepository,
        jwtTokenManager: JwtTokenManager,
    ): AccountComponent {
        return object : AccountComponent(),
            AccountModule by AccountModule(userInfoManager, profileRepository, jwtTokenManager) {}
    }

    @Provides
    @Singleton
    fun provideReactionsComponent(
        userInfoManager: UserInfoManager,
        reactionsRepository: ReactionsRepository,
    ): ReactionsComponent {
        return object : ReactionsComponent(),
            ReactionsModule by ReactionsModule(userInfoManager, reactionsRepository) {}
    }

    @Provides
    @Singleton
    fun provideDialogComponent(
        userInfoManager: UserInfoManager,
        chatsRepository: ChatsRepository,
    ): DialogComponent {
        return object : DialogModule by DialogModule(userInfoManager, chatsRepository),
            DialogComponent() {}
    }
}
