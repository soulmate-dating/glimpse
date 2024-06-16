package ru.hse.glimpse.network.api.chats.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChatInfo(

    @SerializedName("companion")
    val companion: Companion,

    @SerializedName("last_message")
    val lastMessage: String,

    @SerializedName("is_your_turn")
    val isYourTurn: Boolean,
) {

    @Keep
    data class Companion(

        @SerializedName("id")
        val id: String,

        @SerializedName("first_name")
        val firstName: String,

        @SerializedName("last_name")
        val lastName: String,

        @SerializedName("pic_link")
        val picLink: String,
    )
}
