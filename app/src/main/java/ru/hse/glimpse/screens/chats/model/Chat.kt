package ru.hse.glimpse.screens.chats.model

import androidx.annotation.Keep

@Keep
data class Chat(
    val imageLink: String,
    val name: String,
    val lastMessage: String,
)
