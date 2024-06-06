package ru.hse.glimpse.network.api.chats

import com.skydoves.sandwich.ApiResponse
import ru.hse.glimpse.network.api.chats.model.ChatInfo

class ChatsRepository(
    private val chatsApi: ChatsApi,
) {
    suspend fun getCompanions(userId: String): ApiResponse<List<ChatInfo>> {
        return chatsApi.getCompanions(userId)
    }
}
