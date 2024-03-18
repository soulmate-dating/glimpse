package ru.hse.glimpse.screens.chats.ui.data

import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemChatBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class ChatItem(
    val imageLink: String = "https://www.pngall.com/wp-content/uploads/2/Leonardo-Dicaprio.png",
    val name: String = "Leo",
    val lastMessage: String = "Wanna see my Oscar?",
) : ViewTyped {

    override val uid: String
        get() = imageLink + name

    override val viewType: Int
        get() = R.layout.item_chat
}

class ChatsViewHolder(
    view: View,
    clicks: TiRecyclerClickListener
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
    }
}
