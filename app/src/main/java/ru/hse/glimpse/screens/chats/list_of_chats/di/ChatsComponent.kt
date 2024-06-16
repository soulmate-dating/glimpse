package ru.hse.glimpse.screens.chats.list_of_chats.di

import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsStore
import ru.hse.glimpse.screens.chats.list_of_chats.ui.mapper.ChatsUiStateMapper
import ru.hse.glimpse.utils.di.BaseComponent

abstract class ChatsComponent : BaseComponent {

    abstract fun createChatsStore(): ChatsStore
    abstract val uiStateMapper: ChatsUiStateMapper
}
