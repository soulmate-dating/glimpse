package ru.hse.glimpse.network.api.chats.model

import com.google.gson.annotations.SerializedName

data class MessagesResponse(
    val messages: List<Message>,
) {

    data class Message(
        val id: String,
        @SerializedName("sender_id")
        val senderId: String,
        @SerializedName("recipient_id")
        val recipientId: String,
        val date: String,
        val content: String,
        val tag: Tag,
    )

    data class Tag(
        val name: String,
        val externalId: String,
    )
}
