package ru.hse.glimpse.screens.chats.list_of_chats.ui.data

import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemChatBinding
import ru.hse.glimpse.network.api.chats.model.ChatInfo
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class ChatItem(
    val imageLink: String,
    val name: String = "Leo",
    val lastMessage: String = "Wanna see my Oscar?",
    val chatInfo: ChatInfo,
) : ViewTyped {

    override val uid: String
        get() = imageLink + name

    override val viewType: Int
        get() = R.layout.item_chat
}

class ChatsViewHolder(
    view: View,
    clicks: TiRecyclerClickListener,
    private val onChatClickListener: (ChatInfo) -> Unit,
) : BaseViewHolder<ChatItem>(view, clicks) {

    private val binding = ItemChatBinding.bind(view)

    override fun bind(item: ChatItem) {
        binding.profileImage.load(item.imageLink) {
            crossfade(true)
            placeholder(R.drawable.icon_account_circle)
            transformations(CircleCropTransformation())
        }
        binding.name.text = item.name
        binding.lastMessage.text = item.lastMessage
        binding.root.setOnClickListener { onChatClickListener(item.chatInfo) }
    }
}
