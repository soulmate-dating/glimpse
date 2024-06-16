package ru.hse.glimpse.network.api.chats

import com.skydoves.sandwich.ApiResponse
import ru.hse.glimpse.network.api.chats.model.ChatInfo
import ru.hse.glimpse.network.api.chats.model.MessagesResponse
import ru.hse.glimpse.network.api.chats.model.SendMessageBody

class ChatsRepository(
    private val chatsApi: ChatsApi,
) {

    suspend fun getCompanions(userId: String): ApiResponse<List<ChatInfo>> {
        return chatsApi.getCompanions(userId)
    }

    suspend fun getMessages(
        currentUserId: String,
        companionId: String,
    ): ApiResponse<MessagesResponse> {
        return chatsApi.getMessages(currentUserId, companionId)
    }

    suspend fun sendMessage(
        currentUserId: String,
        sendMessageBody: SendMessageBody,
    ): ApiResponse<Unit> {
        return chatsApi.sendMessage(currentUserId, sendMessageBody)
    }
}
