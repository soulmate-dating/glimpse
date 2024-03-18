package ru.hse.glimpse.screens.chats.ui.recycler

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.screens.chats.ui.data.ChatsViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class ChatsViewHolderFactory : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_chat -> ChatsViewHolder(view, clicks)
            else -> null
        }
    }
}
