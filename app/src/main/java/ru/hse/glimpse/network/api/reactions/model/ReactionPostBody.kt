package ru.hse.glimpse.network.api.reactions.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ReactionPostBody(

    @SerializedName("recepient_id")
    val recepientId: String,

    @SerializedName("comment")
    val comment: String,

    @SerializedName("prompt_id")
    val promptId: String,
)
