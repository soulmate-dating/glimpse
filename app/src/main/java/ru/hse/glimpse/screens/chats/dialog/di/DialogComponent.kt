package ru.hse.glimpse.screens.chats.dialog.di

import ru.hse.glimpse.screens.chats.dialog.presentation.DialogStore
import ru.hse.glimpse.utils.di.BaseComponent

abstract class DialogComponent : BaseComponent {
    abstract fun createDialogStore(): DialogStore
}
