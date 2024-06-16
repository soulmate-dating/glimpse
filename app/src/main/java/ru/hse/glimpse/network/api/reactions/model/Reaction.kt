package ru.hse.glimpse.network.api.reactions.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Reaction(
    val id: String,
    val sender: Sender,
    val comment: String,
    @SerializedName("prompt_id")
    val promptId: String,
    @SerializedName("sent_at")
    val sentAt: String,
    val state: String,
) {

    data class Sender(
        val id: String,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("second_name")
        val secondName: String,
        @SerializedName("avatar_link")
        val avatarLink: String,
    )
}
