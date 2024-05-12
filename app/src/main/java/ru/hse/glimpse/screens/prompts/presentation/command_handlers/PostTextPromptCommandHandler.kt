package ru.hse.glimpse.screens.prompts.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.retry.RetryPolicy
import com.skydoves.sandwich.retry.runAndRetry
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand.SendPrompt
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class PostTextPromptCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val promptsRepository: PromptsRepository,
) : CommandsFlowHandler<PromptsCommand, PromptsEvent> {

    val retryPolicy = object : RetryPolicy {
        override fun shouldRetry(attempt: Int, message: String?): Boolean = attempt <= 3

        override fun retryTimeout(attempt: Int, message: String?): Int = 3000
    }

    override fun handle(commands: Flow<PromptsCommand>): Flow<PromptsEvent> {
        return commands.filterIsInstance<SendPrompt>()
            .transform { command ->
                runAndRetry(retryPolicy) { _, _ ->
                    promptsRepository.postTextPrompt(
                        userInfoManager.getUserId() ?: "",
                        listOf(command.prompt)
                    )
                }.suspendOnSuccess {
                    emit(PostPromptSuccess(this.data.data.first()))
                }.suspendOnFailure {
                    emit(PostPromptError(this.message()))
                }
            }
    }
}
