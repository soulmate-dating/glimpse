package ru.hse.glimpse.screens.chats.dialog.ui.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogState
import ru.hse.glimpse.screens.chats.dialog.ui.data.DateDividerItem
import ru.hse.glimpse.screens.chats.dialog.ui.data.MyMessageItem
import ru.hse.glimpse.screens.chats.dialog.ui.data.OthersMessageItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale
import java.util.TimeZone

class DialogUiStateMapper : UiStateMapper<DialogState, DialogUiState> {

    private val differenceBetweenDefaultZone: Long
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            // Получаем текущую временную зону и ее смещение относительно UTC в миллисекундах
            val timeZone = TimeZone.getDefault()
            val offsetMs = timeZone.getOffset(System.currentTimeMillis())
            // Конвертируем смещение в часы
            val offsetHours = offsetMs / (1000 * 60 * 60)
            return offsetHours.toLong()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun ResourcesProvider.map(state: DialogState): DialogUiState {
        val items = if (state.messagesResponse == null) {
            emptyList()
        } else {
            var currentDate: String? = null
            buildList {
                state.messagesResponse.messages.reversed().forEach { message ->
                    val localDateTime = ZonedDateTime.parse(message.date)
                        .plusHours(differenceBetweenDefaultZone)

                    val time = "${localDateTime.hour}:${localDateTime.minute.toString().padStart(2, '0')}"
                    val date = if (localDateTime.toLocalDate().equals(LocalDate.now(localDateTime.zone))) {
                        "Today"
                    } else {
                        "${localDateTime.month.getDisplayName(TextStyle.FULL, Locale.US)} ${localDateTime.dayOfMonth}"
                    }

                    if (currentDate == null || currentDate != date) {
                        add(DateDividerItem(date = date))
                        currentDate = date
                    }

                    if (message.senderId == state.companionId) {
                        add(
                            OthersMessageItem(
                                content = message.content,
                                message = message,
                                time = time,
                            )
                        )
                    } else {
                        add(
                            MyMessageItem(
                                content = message.content,
                                message = message,
                                time = time,
                            )
                        )
                    }
                }
            }
        }
        return DialogUiState(items = items.reversed())
    }
}
