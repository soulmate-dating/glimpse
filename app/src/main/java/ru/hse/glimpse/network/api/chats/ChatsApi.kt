package ru.hse.glimpse.network.api.chats

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import ru.hse.glimpse.network.api.chats.model.ChatInfo

interface ChatsApi {

    @GET("users/{user_id}/companions")
    suspend fun getCompanions(
        @Path("user_id") userId: String,
    ): ApiResponse<List<ChatInfo>>
}
