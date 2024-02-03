package ru.hse.glimpse.screens.entrypoint.presentation

import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointEvent
import ru.hse.glimpse.screens.entrypoint.presentation.news.EntrypointNews
import ru.hse.glimpse.screens.entrypoint.presentation.state.EntrypointState
import ru.tinkoff.kotea.core.Store

internal typealias EntrypointStore = Store<EntrypointState, EntrypointEvent, EntrypointNews>
