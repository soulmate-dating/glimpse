package ru.hse.glimpse.main.presentation

import ru.hse.glimpse.main.presentation.event.MainEvent
import ru.hse.glimpse.main.presentation.news.MainNews
import ru.hse.glimpse.main.presentation.state.MainState
import ru.tinkoff.kotea.core.Store

internal typealias MainStore = Store<MainState, MainEvent, MainNews>
