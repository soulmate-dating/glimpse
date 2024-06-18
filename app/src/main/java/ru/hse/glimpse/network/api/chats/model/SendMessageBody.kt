package ru.hse.glimpse.network.api.chats.model

import com.google.gson.annotations.SerializedName

data class SendMessageBody(
    @SerializedName("recipient_id")
    val recipientId: String,
    val content: String,
)
