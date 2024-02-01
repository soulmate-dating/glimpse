package ru.hse.glimpse.entrypoint.presentation

import ru.hse.glimpse.entrypoint.presentation.event.EntrypointEvent
import ru.hse.glimpse.entrypoint.presentation.news.EntrypointNews
import ru.hse.glimpse.entrypoint.presentation.state.EntrypointState
import ru.tinkoff.kotea.core.Store

internal typealias EntrypointStore = Store<EntrypointState, EntrypointEvent, EntrypointNews>
