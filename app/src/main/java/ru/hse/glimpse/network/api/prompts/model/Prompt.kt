package ru.hse.glimpse.network.api.prompts.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Prompt(
    val content: String,
    val id: String? = null,
    val position: Int? = null,
    val question: String,
    val type: PromptType,
) {

    @Keep
    enum class PromptType {

        @SerializedName("text")
        TEXT,

        @SerializedName("image")
        IMAGE;

        override fun toString(): String {
            return name.lowercase()
        }
    }
}
