package ru.hse.glimpse.screens.main.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.main.presentation.MainCommand
import ru.hse.glimpse.screens.main.presentation.MainCommand.LoadRecommendation
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.LoadRecommendationError
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.LoadRecommendationSuccess
import ru.hse.glimpse.screens.main.presentation.MainEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadRecommendationCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val profileRepository: ProfileRepository,
) : CommandsFlowHandler<MainCommand, MainEvent> {
    override fun handle(commands: Flow<MainCommand>): Flow<MainEvent> {
        return commands.filterIsInstance<LoadRecommendation>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            profileRepository.getRecommendation(userId)
                .suspendOnSuccess { emit(LoadRecommendationSuccess(this.data.data)) }
                .suspendOnFailure { emit(LoadRecommendationError(this.message())) }
        }
    }
}
