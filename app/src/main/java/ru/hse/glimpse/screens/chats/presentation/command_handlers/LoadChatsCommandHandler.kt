package ru.hse.glimpse.screens.chats.presentation.command_handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.hse.glimpse.network.api.chats.model.ChatSummary
import ru.hse.glimpse.network.api.chats.model.CompanionResponse
import ru.hse.glimpse.screens.chats.presentation.ChatsCommand
import ru.hse.glimpse.screens.chats.presentation.ChatsCommandResultEvent.ChatsError
import ru.hse.glimpse.screens.chats.presentation.ChatsEvent
import ru.hse.glimpse.screens.chats.presentation.ChatsCommandResultEvent.ChatsLoaded
import ru.hse.glimpse.utils.coroutines.runCatchingCancellable
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadChatsCommandHandler : CommandsFlowHandler<ChatsCommand, ChatsEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<ChatsCommand>): Flow<ChatsEvent> {
        return commands.filterIsInstance<ChatsCommand.LoadChats>()
            .mapLatest {
                runCatchingCancellable {
                    getChats()
                }
                    .map { ChatsLoaded(it.companions) }
                    .getOrElse(::ChatsError)
            }
    }

    private suspend fun getChats(): CompanionResponse {
        delay(1000)

        return CompanionResponse(
            listOf(
                ChatSummary(
                    ChatSummary.Companion(
                        id = "id",
                        firstName = "Dima",
                        lastName = "Kutuzov",
                        picLink = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/535891_v9_bc.jpg",
                    ),
                    lastMessage = "Kutuzov",
                    isYourTurn = true,
                ),
                ChatSummary(
                    ChatSummary.Companion(
                        id = "id",
                        firstName = "Dima",
                        lastName = "Kutuzov",
                        picLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9RtMbdLcmmDJzVk4ficc8hv726M4XDBfu_0Ouk4g63A&s",
                    ),
                    lastMessage = "Kutuzov",
                    isYourTurn = true,
                ),
                ChatSummary(
                    ChatSummary.Companion(
                        id = "id",
                        firstName = "Dima",
                        lastName = "Kutuzov",
                        picLink = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/535891_v9_bc.jpg",
                    ),
                    lastMessage = "Kutuzov",
                    isYourTurn = true,
                ),
                ChatSummary(
                    ChatSummary.Companion(
                        id = "id",
                        firstName = "Dima",
                        lastName = "Kutuzov",
                        picLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9RtMbdLcmmDJzVk4ficc8hv726M4XDBfu_0Ouk4g63A&s",
                    ),
                    lastMessage = "Kutuzov",
                    isYourTurn = true,
                ),
                ChatSummary(
                    ChatSummary.Companion(
                        id = "id",
                        firstName = "Dima",
                        lastName = "Kutuzov",
                        picLink = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/535891_v9_bc.jpg",
                    ),
                    lastMessage = "Kutuzov",
                    isYourTurn = false,
                ),
                ChatSummary(
                    ChatSummary.Companion(
                        id = "id",
                        firstName = "Dima",
                        lastName = "Kutuzov",
                        picLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9RtMbdLcmmDJzVk4ficc8hv726M4XDBfu_0Ouk4g63A&s",
                    ),
                    lastMessage = "Kutuzov",
                    isYourTurn = false,
                ),
                ChatSummary(
                    ChatSummary.Companion(
                        id = "id",
                        firstName = "Dima",
                        lastName = "Kutuzov",
                        picLink = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/535891_v9_bc.jpg",
                    ),
                    lastMessage = "Kutuzov",
                    isYourTurn = false,
                ),
            )
        )
    }
}
