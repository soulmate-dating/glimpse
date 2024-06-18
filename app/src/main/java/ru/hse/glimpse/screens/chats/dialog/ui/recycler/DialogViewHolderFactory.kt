package ru.hse.glimpse.screens.chats.dialog.ui.recycler

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.screens.chats.dialog.ui.data.DateDividerViewHolder
import ru.hse.glimpse.screens.chats.dialog.ui.data.MyMessageViewHolder
import ru.hse.glimpse.screens.chats.dialog.ui.data.OthersMessageViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class DialogViewHolderFactory : CoroutinesHolderFactory() {
    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_my_message -> MyMessageViewHolder(view)
            R.layout.item_others_message -> OthersMessageViewHolder(view)
            R.layout.item_chat_date_divider -> DateDividerViewHolder(view)
            else -> null
        }
    }
}
