package ru.hse.glimpse.screens.fill_profile.di

import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileCommand.LoadProfile
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileState
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileStore
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileUpdate
import ru.hse.glimpse.screens.fill_profile.presentation.command_handlers.LoadProfileCommandHandler
import ru.hse.glimpse.screens.fill_profile.presentation.command_handlers.SendNewProfileCommandHandler
import ru.hse.glimpse.screens.fill_profile.presentation.command_handlers.UpdateProfileCommandHandler
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface FillProfileModule {
    fun createFillProfileStore(): FillProfileStore
}

fun FillProfileModule(
    profileRepository: ProfileRepository,
    userInfoManager: UserInfoManager,
): FillProfileModule {
    return object : FillProfileModule {
        override fun createFillProfileStore(): FillProfileStore {
            return KoteaStore(
                initialState = FillProfileState(),
                initialCommands = listOf(LoadProfile),
                commandsFlowHandlers = listOf(
                    SendNewProfileCommandHandler(
                        profileRepository = profileRepository,
                        userInfoManager = userInfoManager,
                    ),
                    LoadProfileCommandHandler(
                        userInfoManager = userInfoManager,
                        profileRepository = profileRepository,
                    ),
                    UpdateProfileCommandHandler(
                        userInfoManager = userInfoManager,
                        profileRepository = profileRepository,
                    ),
                ),
                update = FillProfileUpdate(),
            )
        }
    }
}
