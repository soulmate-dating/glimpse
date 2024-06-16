package ru.hse.glimpse.network.api.chats

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.hse.glimpse.network.api.chats.model.ChatInfo
import ru.hse.glimpse.network.api.chats.model.MessagesResponse
import ru.hse.glimpse.network.api.chats.model.SendMessageBody

interface ChatsApi {

    @GET("users/{user_id}/companions")
    suspend fun getCompanions(
        @Path("user_id") userId: String,
    ): ApiResponse<List<ChatInfo>>

    @GET("users/{id}/messages")
    suspend fun getMessages(
        @Path("id") currentUserId: String,
        @Query("companion_id") companionId: String,
    ): ApiResponse<MessagesResponse>

    @POST("users/{id}/messages")
    suspend fun sendMessage(
        @Path("id") currentUserId: String,
        @Body sendMessageBody: SendMessageBody,
    ): ApiResponse<Unit>
}
