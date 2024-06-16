package ru.hse.glimpse.screens.chats.dialog.presentation

sealed interface DialogEvent

sealed interface DialogCommandResultEvent : DialogEvent

sealed interface DialogUiEvent : DialogEvent {
    object OnInit : DialogUiEvent
    object BackClicked : DialogUiEvent
}
