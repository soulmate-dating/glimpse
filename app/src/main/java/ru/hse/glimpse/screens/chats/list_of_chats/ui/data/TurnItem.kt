package ru.hse.glimpse.screens.chats.list_of_chats.ui.data

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemTurnBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class TurnItem(
    val isYourTurn: Boolean,
    val amountOfChats: Int,
) : ViewTyped {
    override val uid: String
        get() = isYourTurn.toString() + amountOfChats.toString()
    override val viewType: Int
        get() = R.layout.item_turn
}

class TurnViewHolder(
    view: View,
) : BaseViewHolder<TurnItem>(view) {

    private val binding = ItemTurnBinding.bind(view)

    override fun bind(item: TurnItem) {
        val context = binding.root.context
        val amount = item.amountOfChats.toString()
        binding.root.text = context.getString(
            if (item.isYourTurn) R.string.your_turn else R.string.their_turn,
            amount,
        )
    }
}
