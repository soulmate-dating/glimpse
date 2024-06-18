package ru.hse.glimpse.screens.chats.dialog.ui.data

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemOthersMessageBinding
import ru.hse.glimpse.network.api.chats.model.MessagesResponse
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class OthersMessageItem(
    val content: String,
    val time: String = "10:11",
    val message: MessagesResponse.Message,
) : ViewTyped {
    override val uid: String
        get() = message.id
    override val viewType: Int
        get() = R.layout.item_others_message
}

class OthersMessageViewHolder(view: View) : BaseViewHolder<OthersMessageItem>(view) {

    private val binding = ItemOthersMessageBinding.bind(view)

    override fun bind(item: OthersMessageItem) {
        binding.content.text = item.content
        binding.time.text = item.time
    }
}
