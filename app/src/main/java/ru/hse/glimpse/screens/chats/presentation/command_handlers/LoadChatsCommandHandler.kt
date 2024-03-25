package ru.hse.glimpse.screens.chats.presentation.command_handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.hse.glimpse.screens.chats.model.Chat
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
                    .map(::ChatsLoaded)
                    .getOrElse(::ChatsError)
            }
    }

    private suspend fun getChats(): List<Chat> {
        delay(3000)

        return listOf(
            Chat(
                imageLink = "https://vegnews.com/media/W1siZiIsIjM5NjIzL1ZlZ05ld3MuTGVvbmFyZG9EaUNhcHJpbzMuU2h1dHRlcnN0b2NrLmdpZiJdLFsicCIsImNyb3BfcmVzaXplZCIsIjE0OTV4ODg0KzArMCIsIjE2MDB4OTQ2XiIseyJmb3JtYXQiOiJqcGcifV0sWyJwIiwib3B0aW1pemUiXV0/VegNews.LeonardoDiCaprio3.Shutterstock.gif?sha=4b96fe3240cb8eb3",
                name = "Leo",
                lastMessage = "wanna see my Oscar?",
            ),
            Chat(
                imageLink = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/535891_v9_bc.jpg",
                name = "Timothee",
                lastMessage = "have you ever been to desert?",
            ),
            Chat(
                imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Bill_Clinton.jpg/1200px-Bill_Clinton.jpg",
                name = "Bill",
                lastMessage = "you sound scary",
            ),
            Chat(
                imageLink = "https://vegnews.com/media/W1siZiIsIjM5NjIzL1ZlZ05ld3MuTGVvbmFyZG9EaUNhcHJpbzMuU2h1dHRlcnN0b2NrLmdpZiJdLFsicCIsImNyb3BfcmVzaXplZCIsIjE0OTV4ODg0KzArMCIsIjE2MDB4OTQ2XiIseyJmb3JtYXQiOiJqcGcifV0sWyJwIiwib3B0aW1pemUiXV0/VegNews.LeonardoDiCaprio3.Shutterstock.gif?sha=4b96fe3240cb8eb3",
                name = "Leo",
                lastMessage = "wanna see my Oscar?",
            ),
            Chat(
                imageLink = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/535891_v9_bc.jpg",
                name = "Timothee",
                lastMessage = "have you ever been to desert?",
            ),
            Chat(
                imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Bill_Clinton.jpg/1200px-Bill_Clinton.jpg",
                name = "Bill",
                lastMessage = "you sound scary",
            ),
            Chat(
                imageLink = "https://vegnews.com/media/W1siZiIsIjM5NjIzL1ZlZ05ld3MuTGVvbmFyZG9EaUNhcHJpbzMuU2h1dHRlcnN0b2NrLmdpZiJdLFsicCIsImNyb3BfcmVzaXplZCIsIjE0OTV4ODg0KzArMCIsIjE2MDB4OTQ2XiIseyJmb3JtYXQiOiJqcGcifV0sWyJwIiwib3B0aW1pemUiXV0/VegNews.LeonardoDiCaprio3.Shutterstock.gif?sha=4b96fe3240cb8eb3",
                name = "Leo",
                lastMessage = "wanna see my Oscar?",
            ),
            Chat(
                imageLink = "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/535891_v9_bc.jpg",
                name = "Timothee",
                lastMessage = "have you ever been to desert?",
            ),
            Chat(
                imageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Bill_Clinton.jpg/1200px-Bill_Clinton.jpg",
                name = "Bill",
                lastMessage = "you sound scary",
            ),
        )
    }
}
