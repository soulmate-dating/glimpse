package ru.hse.glimpse.screens.chats.di

import ru.hse.glimpse.screens.chats.presentation.ChatsStore
import ru.hse.glimpse.screens.chats.ui.mapper.ChatsUiStateMapper
import ru.hse.glimpse.utils.di.BaseComponent

abstract class ChatsComponent : BaseComponent {

    abstract fun createChatsStore(): ChatsStore
    abstract val uiStateMapper: ChatsUiStateMapper
}
