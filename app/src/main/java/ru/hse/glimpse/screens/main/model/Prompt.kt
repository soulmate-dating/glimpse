package ru.hse.glimpse.screens.main.model

import androidx.annotation.Keep

// todo: delete and use basic prompt from network module
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
