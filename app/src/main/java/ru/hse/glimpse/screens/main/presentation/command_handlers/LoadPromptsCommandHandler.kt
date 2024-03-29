package ru.hse.glimpse.screens.main.presentation.command_handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.hse.glimpse.screens.main.model.Prompt
import ru.hse.glimpse.screens.main.presentation.MainCommand
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.PromptsFailed
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.PromptsLoaded
import ru.hse.glimpse.screens.main.presentation.MainEvent
import ru.hse.glimpse.utils.coroutines.runCatchingCancellable
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadPromptsCommandHandler : CommandsFlowHandler<MainCommand, MainEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<MainCommand>): Flow<MainEvent> {
        return commands.filterIsInstance<MainCommand.LoadPrompts>()
            .mapLatest {
                runCatchingCancellable { getPrompts() }
                    .map(::PromptsLoaded)
                    .getOrElse(::PromptsFailed)
            }
    }

    private suspend fun getPrompts(): List<Prompt> {
        delay(3000)

        return listOf(
            Prompt(
                type = Prompt.Type.IMAGE,
                id = "0",
                userId = "0",
                content = "something",
                question = null,
                imageLink = "https://cdnn21.img.ria.ru/images/15181/37/151813756_0:0:0:0_600x0_80_0_0_c12faaa4d5130b597e998dfd524c42f9.jpg",
            ),
            Prompt(
                type = Prompt.Type.TEXT,
                id = "1",
                userId = "1",
                content = "Being tortured with a Scorcese movie",
                question = "My most irrational fear",
                imageLink = "https://img06.rl0.ru/afisha/e1000x500i/daily.afisha.ru/uploads/images/a/61/a6120e500cefea9d1f138b7d115cf17a.jpg",
            ),
            Prompt(
                type = Prompt.Type.IMAGE,
                id = "2",
                userId = "2",
                content = "something",
                question = null,
                imageLink = "https://cdnn21.img.ria.ru/images/15181/37/151813756_0:0:0:0_600x0_80_0_0_c12faaa4d5130b597e998dfd524c42f9.jpg",
            ),
            Prompt(
                type = Prompt.Type.TEXT,
                id = "3",
                userId = "3",
                content = "Being tortured with a Scorcese movie",
                question = "My most irrational fear",
                imageLink = "https://img06.rl0.ru/afisha/e1000x500i/daily.afisha.ru/uploads/images/a/61/a6120e500cefea9d1f138b7d115cf17a.jpg",
            ),
        )
    }
}
