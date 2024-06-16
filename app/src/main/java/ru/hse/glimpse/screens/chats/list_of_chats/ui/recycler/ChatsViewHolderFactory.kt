package ru.hse.glimpse.screens.chats.list_of_chats.ui.recycler

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.network.api.chats.model.ChatInfo
import ru.hse.glimpse.screens.chats.list_of_chats.ui.data.ChatsViewHolder
import ru.hse.glimpse.screens.chats.list_of_chats.ui.data.DividerViewHolder
import ru.hse.glimpse.screens.chats.list_of_chats.ui.data.TurnViewHolder
import ru.hse.glimpse.screens.common.recycler.items.EmptyStateViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class ChatsViewHolderFactory(
    private val onChatClickListener: (ChatInfo) -> Unit,
) : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_chat -> ChatsViewHolder(
                view,
                clicks,
                onChatClickListener,
            )
            R.layout.item_turn -> TurnViewHolder(view)
            R.layout.item_divider_chats -> DividerViewHolder(view)
            R.layout.item_empty_state -> EmptyStateViewHolder(view)
            else -> null
        }
    }
}
