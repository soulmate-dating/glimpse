package ru.hse.glimpse.screens.fill_profile.di

import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileState
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileStore
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileUpdate
import ru.hse.glimpse.screens.fill_profile.presentation.command_handlers.SendProfileCommandHandler
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
                initialCommands = emptyList(),
                commandsFlowHandlers = listOf(
                    SendProfileCommandHandler(
                        profileRepository = profileRepository,
                        userInfoManager = userInfoManager,
                    ),
                ),
                update = FillProfileUpdate(),
            )
        }
    }
}
