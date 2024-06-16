package ru.hse.glimpse.screens.chats.dialog.presentation

import ru.hse.glimpse.network.api.chats.model.MessagesResponse

sealed interface DialogEvent

sealed interface DialogCommandResultEvent : DialogEvent {
    data class GetMessagesSuccess(val messagesResponse: MessagesResponse) : DialogCommandResultEvent
    data class GetMessagesError(val message: String?) : DialogCommandResultEvent

    object SendMessagesSuccess : DialogCommandResultEvent
    data class SendMessagesError(val message: String?) : DialogCommandResultEvent
}

sealed interface DialogUiEvent : DialogEvent {
    data class OnInit(val companionId: String) : DialogUiEvent
    data class SendMessage(val content: String) : DialogUiEvent
    object BackClicked : DialogUiEvent
    data class GetMessages(val companionId: String) : DialogUiEvent
}
