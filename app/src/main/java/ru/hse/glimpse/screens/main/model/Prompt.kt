package ru.hse.glimpse.screens.main.model

import androidx.annotation.Keep

@Keep
data class Prompt(
    val type: Type,
    val id: String,
    val userId: String,
    val content: String,
    val question: String?,
    val imageLink: String?,
) {

    enum class Type {
        TEXT,
        IMAGE,
    }
}
