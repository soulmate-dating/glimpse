package ru.hse.glimpse.screens.chats.dialog.ui.data

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemMyMessageBinding
import ru.hse.glimpse.network.api.chats.model.MessagesResponse
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class MyMessageItem(
    val content: String,
    val time: String = "20:11",
    val message: MessagesResponse.Message,
) : ViewTyped {
    override val uid: String
        get() = message.id
    override val viewType: Int
        get() = R.layout.item_my_message
}

class MyMessageViewHolder(view: View) : BaseViewHolder<MyMessageItem>(view) {

    private val binding = ItemMyMessageBinding.bind(view)

    override fun bind(item: MyMessageItem) {
        binding.content.text = item.content
        binding.time.text = item.time
    }
}
